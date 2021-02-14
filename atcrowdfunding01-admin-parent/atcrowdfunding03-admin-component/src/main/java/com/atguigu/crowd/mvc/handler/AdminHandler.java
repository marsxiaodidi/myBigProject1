package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.exception.HasAdminUserException;
import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.impl.AdminServiceImpl;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 10185
 * @date 2021/2/2 18:13
 * 设置Admin处理器类
 */
@Controller
@RequestMapping("/admin")
public class AdminHandler {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login/ssm.html")
    public String login(@RequestParam("loginAcct") String loginAcct
            , @RequestParam("password") String password
            , HttpServletRequest request) throws FailedLoginException {

        request.setAttribute("username", loginAcct);
        Admin admin = adminService.loginAdmin(loginAcct, password);
        HttpSession session = request.getSession();
        session.setAttribute("AdminUser", admin);
        System.out.println("我jdfg猪");
        return "redirect:/admin/to/login/admin-main.html";
    }


    @RequestMapping("/exit/ssm.html")
    public String exit(HttpSession session) {
        //清除session里面的数据
        session.invalidate();
        //然后重定向,通过在springmvc配置文件中的直接转发直接转发到admin-login页面
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("/search/ssm.html")
    public String search(@RequestParam(value = "keyCard", defaultValue = "") String keyCard
            , @RequestParam(value = "pageNum", defaultValue = "1") String pageNum
            , @RequestParam(value = "pageSize", defaultValue = "5") String pageSize, HttpServletRequest request) {
        PageInfo<Admin> pageInfo = adminService.getPageInfoList(keyCard, pageNum, pageSize);
        request.setAttribute("pageInfo", pageInfo);
        return "admin-page";
    }

    @RequestMapping("/remove/{id}/{pageNum}/{keyCard}.html")
    public String remove(@PathVariable Integer id,@PathVariable Integer pageNum,@PathVariable String keyCard) {
         adminService.deleteAdmin(id);
         return "redirect:/admin/search/ssm.html?pageNum="+pageNum+"&keyCard="+keyCard;

    }
    @RequestMapping(value = "/updatePage/{id}/{pageNum}/{keyCard}.html")
    public ModelAndView updatePage(@PathVariable Integer id,@PathVariable Integer pageNum,@PathVariable String keyCard) {
        Admin adminById = adminService.getAdminById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin", adminById);
        modelAndView.addObject("pageNum1", pageNum);
        modelAndView.addObject("keyCard1", keyCard);
        modelAndView.setViewName("admin-update");
        return modelAndView;
    }
    @RequestMapping(value = "/update/{id}/{pageNum}/{keyCard}.html",method = RequestMethod.POST)
    public String update(@PathVariable Integer id,@PathVariable Integer pageNum,@PathVariable String keyCard,Admin admin) {
        admin.setId(id);
        adminService.updateAdmin(admin);
        return "redirect:/admin/search/ssm.html?pageNum="+pageNum+"&keyCard="+keyCard;

    }
    @RequestMapping("save/ssm.html")
    public String save(Admin admin) {
        Date date = new Date();
        String userPswd = admin.getUserPswd();
        String mp5 = CrowdUtil.getMp5(userPswd);

        admin.setUserPswd(mp5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        admin.setCreateTime(format);
        try {
            adminService.saveAdmin(admin);
        } catch (Exception ex) {
            throw new HasAdminUserException("账号重复");
        }
        return "redirect:/admin/search/ssm.html?pageNum="+Integer.MAX_VALUE;

    }
    @RequestMapping("/get24/ssm.html")
    @ResponseBody
    public String get24Test(@RequestParam("text")String text) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] s = text.split(" ");
        for (String s1 : s) {
            Integer i = Integer.parseInt(s1);
            arrayList.add(i);

        }
        Integer[] integers1 = new Integer[]{};
        Integer[] integers2 = arrayList.toArray(integers1);
        ArrayList<Integer[]> integers = new ArrayList<>();
        getDiff(integers,integers2,0);


        //封装+,-,*,/
        String [] strings = {"+","-","*","/"};
        //取出排列组合后的每一个integer
        for (Integer[] integer : integers) {
            for (String str1 : strings) {
                for (String str2 : strings) {
                    for (String str3 : strings) {

                        String result = null;


                        result = "(("+integer[0] +str1+integer[1]+")";

                        result += str2+integer[2]+")";

                        result += str3+integer[3];


                        try {
                            Object aDouble = getDouble(result);
                            if (aDouble.toString().equals("24")) {
                                return result;

                            }
                        } catch (ScriptException  e) {
                            e.printStackTrace();
                        }


                        //(a+b)*(c+d) ac ad bc bd
                        result = "("+integer[0] +str1+integer[1]+")"+str2+"("+integer[2]+str3+integer[3]+")";
                        try {
                            Object aDouble = getDouble(result);
                            if (aDouble.toString().equals("24")) {
                               /* System.out.println(result);*/
                                return result;
                            }
                        } catch (ScriptException  e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }



        return "没有";
    }



    /**
     * 进行不同的排列组合,通过index指向当前的数，用i循环代表可以进行交换的数
     * @return
     */
    public void getDiff(List<Integer[]> allIds,Integer[] ids,Integer index){
        if (ids == null || ids.length == 0 || index < 0 || index >= ids.length) {
            System.out.println("数据错误");
            return;
        }

        //如果index和数组长度相等代表指向了最后一个数了
        if (index == ids.length-1){
            Integer[] clone = ids.clone();
            allIds.add(clone);
            return;

        }


        //让id[index]和id[i]进行交换
        for (int i = index; i < ids.length; i++) {
            ids = swap(ids, index, i);
            getDiff(allIds,ids,index+1);
            //交换回来，以便进行新的交换
            ids = swap(ids,index,i);


        }
        /**
         * 1234 1243 1324 1342 1423 1432
         */
        //首先先把最后的两位进行交换，



    }


    /**
     * 进行交换
     * @param a
     * @param b
     * @return
     */
    public Integer[] swap(Integer[] ids,int a,int b){
        Integer id = ids[a];
        ids[a] = ids[b];
        ids[b] = id;
        return ids;

    }



    public Object getDouble(String str) throws ScriptException {
      /*  char[] chars={'1','2','3','4'};

        p(chars,0);*/
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine js = scriptEngineManager.getEngineByName("js");
        Object eval = js.eval(str);

        return eval;
    }


}

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

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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


}

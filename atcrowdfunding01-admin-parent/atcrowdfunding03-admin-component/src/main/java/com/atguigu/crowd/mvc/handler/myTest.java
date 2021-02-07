package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.ResultEntity;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class myTest {
    @Autowired
    AdminService adminService;
    @RequestMapping("login/ssm.html")
    public String login(Model model) {
        List<Admin> all = adminService.getAll();
        model.addAttribute("admins", all);
        return "success";

    }
   /* @RequestMapping(value = "test2/ssm.html",method = RequestMethod.POST)

    public void test2(@RequestParam("array[]") int[] array, HttpServletResponse response) throws IOException {
        System.out.println(Arrays.toString(array));
        response.getWriter().write("我成功了");


    }*/
   @RequestMapping(value = "test2/ssm.json",method = RequestMethod.POST)

  /* @ResponseBody
   public Date test2(@RequestBody Integer[] array, HttpServletResponse response) throws IOException {
       System.out.println(Arrays.toString(array));
       return new Date();


   }*/
   @ResponseBody
   public ResultEntity<Integer[]> test2(@RequestBody Integer[] array, HttpServletResponse response) throws IOException {
       System.out.println(Arrays.toString(array));
       System.out.println("进行热11部署");
       System.out.println("热部署");

       return ResultEntity.successWithOutDate();


   }
}

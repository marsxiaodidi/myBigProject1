package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.po.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 10185
 * @create 2021/2/9 10:42
 */
@Controller
@RequestMapping("/assign")
public class AssignHandler {
    @Autowired
    RoleService roleService;
    @Autowired
    AdminService adminService;
    @RequestMapping("/getAdminRole/ssm.html")
    public String getAdminRole(Model model,@RequestParam("adminId") Integer id) {
        List<Role> alreadyAssignRole = roleService.getAlreadyAssignRole(id);
        List<Role> notAssignRole = roleService.getNotAssignRole(id);
        model.addAttribute("alreadyAssignRole",alreadyAssignRole);
        model.addAttribute("notAssignRole", notAssignRole);
        return "assign-page";
    }

    @RequestMapping(value = "/assignSaveAdmin/{adminId}/{pageNum}/{keyCard}.html",method = RequestMethod.POST)
    public String assignSaveAdmin(Model model, @PathVariable("adminId") Integer id, @PathVariable("pageNum") Integer pageNum, @PathVariable("keyCard") String keyCard, @RequestParam(value = "ids",required = false) Integer[] ids) {

        //删除原来adminId为id的两个表中的数据//写到adminMapper中
        adminService.deleteAdminIdFromInner(id);
        //添加新的id
        if (ids != null && ids.length != 0) {

            adminService.addRoleByThisAdminByInner(id,ids);

        }
        return "redirect:/admin/search/ssm.html?pageNum="+pageNum+"&keyCard="+keyCard;

    }
}

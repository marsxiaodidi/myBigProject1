package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.po.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 10185
 * @create 2021/2/5 10:41
 */
@Controller
@RequestMapping("/role")
public class RoleHandler {
    @Autowired
    RoleService roleService;
    @RequestMapping("/search/ssm.json")
    @ResponseBody
    public ResultEntity<PageInfo<Role>> search(@RequestParam("pageNum")String pageNum,@RequestParam("pageSize")String pageSize,@RequestParam("keyCard")String keyCard) {
        try {

            PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyCard);
            return ResultEntity.successWithData(pageInfo);
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }


    }
    @RequestMapping("/save/ssm.json")
    @ResponseBody
    public ResultEntity<Object> save(Role role) {
        try {
            roleService.addRole(role);
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            return ResultEntity.failed("用户名已经重复");
        }


    }
    @RequestMapping("/update/ssm.json")
    @ResponseBody
    public ResultEntity<Object> update(Role role) {
        try {
            roleService.updateRole(role);
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            return ResultEntity.failed("用户名已经重复");
        }


    }
    @RequestMapping("/delete/ssm.json")
    @ResponseBody
    public ResultEntity<Object> delete(@RequestParam("id")Integer id) {
        try {
            roleService.deleteRole(id);
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            return ResultEntity.failed("用户名已经重复");
        }


    }
    @RequestMapping("/deleteMany/ssm.json")
    @ResponseBody
    public ResultEntity<Object> delete(@RequestBody Integer[] roleIds) {
        try {
            roleService.deleteRolesByIds(roleIds);
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            return ResultEntity.failed("删除失败");
        }


    }









}

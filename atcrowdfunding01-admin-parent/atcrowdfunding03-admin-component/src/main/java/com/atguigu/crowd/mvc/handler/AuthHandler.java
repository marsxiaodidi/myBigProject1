package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.po.Auth;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.AuthService;
import com.atguigu.crowd.service.impl.AuthServiceImpl;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthHandler {
    @Autowired
    AuthService authService;
    @RequestMapping("/getTree/ssm.html")
    public ResultEntity<Auth> getTree() {
        return null;



    }

}

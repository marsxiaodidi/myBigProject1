package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrownConstant;
import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.exception.HasAdminUserException;
import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice

public class CrowdExceptionResolver {
    @ExceptionHandler(NullPointerException.class)
    public String resolveNullException(HttpServletResponse response, HttpServletRequest request, NullPointerException ex) throws IOException {
        return commonResolve(response, request, ex,"error");


    }
    @ExceptionHandler(Exception.class)
    public String resolveException(HttpServletResponse response, HttpServletRequest request, Exception ex) throws IOException {
        return commonResolve(response, request, ex,"error");


    }
    @ExceptionHandler(FailedLoginException.class)
    public String resolveLoginException(HttpServletResponse response,HttpServletRequest request,Exception ex) throws IOException {
        String viewName = "admin-login";

        return commonResolve(response, request, ex, viewName);
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public String resolveAccessException(HttpServletResponse response, HttpServletRequest request,Exception ex) throws IOException {

        String viewName = "admin-login";
       return commonResolve(response, request, ex, viewName);



    }
    @ExceptionHandler(HasAdminUserException.class)
    public String hasAdminUserException(HttpServletResponse response, HttpServletRequest request,Exception ex) throws IOException {

        String viewName = "admin-add";
        return commonResolve(response, request, ex, viewName);



    }

    private String commonResolve(HttpServletResponse response, HttpServletRequest request, Exception ex, String viewName) throws IOException {
        request.setAttribute("error", ex);
        //如果是ajax请求
        if (CrowdUtil.ifAjax(request)) {
            ResultEntity<Object> ex1 = ResultEntity.failed(ex.getMessage());
            //把变成gson传递过去
            Gson gson = new Gson();
            String s = gson.toJson(ex1);

            response.getWriter().write(s);
            return null;
        } else {
            request.setAttribute("exception", ex);
            return viewName;
        }
    }



}

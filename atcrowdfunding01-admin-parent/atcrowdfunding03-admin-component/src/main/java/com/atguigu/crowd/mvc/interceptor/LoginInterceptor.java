package com.atguigu.crowd.mvc.interceptor;

import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.po.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用于进行admin登录检查
 * @author 10185
 * @create 2021/2/3 15:03
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 不用进行postHandle和afterCompletion,只是进行拦截请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin adminUser = (Admin) session.getAttribute("AdminUser");
        if (adminUser != null) {
            //return true代表放行
            return true;
        }
        throw new AccessForbiddenException("不允许进入,你不是管理员");

    }

}

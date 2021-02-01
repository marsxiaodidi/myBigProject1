package com.atguigu.crowd.util;

import javax.servlet.http.HttpServletRequest;

public class CrowdUtil {
    public static boolean ifAjax(HttpServletRequest request){
        String xmlHttpRequest = request.getHeader("X-Requested-With");
        return xmlHttpRequest!=null;
    }

}

package com.atguigu.crowd.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 */
public class CrowdUtil {
    public static boolean ifAjax(HttpServletRequest request){
        String xmlHttpRequest = request.getHeader("X-Requested-With");
        return xmlHttpRequest!=null;
    }
    public static String getMp5(String resource) {
        if ("".equals(resource) || resource == null) {
            throw new RuntimeException("字符串为空");
        }
        try {
            String algorithmWay = "md5";
            MessageDigest instance = MessageDigest.getInstance(algorithmWay);
            byte[] bytes = resource.getBytes();
            byte[] output = instance.digest(bytes);
            //设置BigInteger的signum
            //signum : -1表示负数、0表示零、1表示正数
            int signum  = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            int hexadecimal = 16;
            //把加密后的结果转换成16进制，然后转为大写
            return bigInteger.toString(hexadecimal).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return null;
    }




}

package com.atguigu.test;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Test01 {

    private String username;
    private String password;

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.setUsername("小迪迪");
        test01.setPassword("123124");

        String password = test01.getPassword();
        System.out.println(password);

    }



}

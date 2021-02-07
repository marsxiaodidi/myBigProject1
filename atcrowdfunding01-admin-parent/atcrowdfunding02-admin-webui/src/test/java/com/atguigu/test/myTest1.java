package com.atguigu.test;


import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.po.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.impl.AdminServiceImpl;
import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class myTest1 {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AdminService adminServiceImpl;

    @Test
    public void test() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setUserName("江豪迪");
        admin.setLoginAcct("12");
        admin.setUserPswd("314141");
        admin.setEmail("10102031@qq.com");
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }

    @Test
    public void test03() {
        //获取Logger对象，这里传入的Class就是当前打印日志的类
        Logger logger = LoggerFactory.getLogger(myTest1.class);
        //等级 DEBUG < INFO < WARN < ERROR
        logger.debug("I am DEBUG!!!");
        logger.debug("I am DEBUG!!!");
        logger.debug("I am DEBUG!!!");

        logger.info("I am INFO!!!");
        logger.info("I am INFO!!!");
        logger.info("I am INFO!!!");

        logger.warn("I am WARN!!!");
        logger.warn("I am WARN!!!");
        logger.warn("I am WARN!!!");

        logger.error("I am ERROR!!!");
        logger.error("I am ERROR!!!");
        logger.error("I am ERROR!!!");
    }

    @Test
    public void test04() {
        for (int i = 0; i < 300; i++) {
            Admin admin = new Admin();
            admin.setId(null);
            admin.setLoginAcct("xiao" + i);
            admin.setUserPswd("123213" + i);
            admin.setUserName("marsxiaodidi" + i);
            admin.setEmail("1023128u3" + i + "@qq.com");
            admin.setCreateTime(null);

            adminServiceImpl.saveAdmin(admin);
        }

    }

    @Test
    public void test05() {
        List<Admin> xiao = adminMapper.findByFuzzyQuery("0");
        for (Admin admin : xiao) {
            System.out.println(admin);
        }
    }

    @Test
    public void Test06() {
        for (int i = 0; i < 100; i++) {
            Role role = new Role();
            role.setId(null);
            role.setName("gege" + i);


            roleMapper.insert(role);


        }

    }

    @Test
    public void Test07() {
        List<Role> rolesByKeyCard = roleMapper.getRolesByKeyCard("");
        for (Role role : rolesByKeyCard) {
            System.out.println(role);
        }
    }

    @Test
    public void Test08(){
        String mp5 = CrowdUtil.getMp5("123123");
        System.out.println(mp5
        );
    }
}


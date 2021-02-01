package com.atguigu.test;


import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.impl.AdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class myTest1 {
    @Autowired
    AdminMapper adminMapper;
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
    public void test03(){
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
        Admin admin = new Admin(null,"xiao","123123","将好滴","101087328974@qq.com",null);
        adminServiceImpl.saveAdmin(admin);

    }
    }

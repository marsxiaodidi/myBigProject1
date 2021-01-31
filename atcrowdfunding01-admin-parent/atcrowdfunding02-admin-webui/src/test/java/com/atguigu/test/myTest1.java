package com.atguigu.test;




import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.po.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class myTest1 {
    @Autowired
    AdminMapper adminMapper;
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
}

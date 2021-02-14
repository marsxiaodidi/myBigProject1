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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;


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
    public void Test08() {
        String mp5 = CrowdUtil.getMp5("123123");
        System.out.println(mp5
        );
    }

    @Test
    public void Test09() {
        List<Role> alreadyAssignRole = roleMapper.getAlreadyAssignRole(1);
        for (Role role : alreadyAssignRole) {
            System.out.println(role);
        }

    }
    /* @Test
     */

    /**
     * 通过加减乘除四个运算随机抽取三个进行运算，三个for语句的循环
     * 同时四个数字随机抽取2个先进行组合然后和第三个数字进行组合，然后和第四个数字进行组合
     */
    @Test
    public void get24Test() {
        ArrayList<Integer[]> integers = new ArrayList<>();
        getDiff(integers, new Integer[]{1, 2, 3, 4}, 0);


        //封装+,-,*,/
        String[] strings = {"+", "-", "*", "/"};
        //取出排列组合后的每一个integer
        for (Integer[] integer : integers) {
            for (String str1 : strings) {
                for (String str2 : strings) {
                    for (String str3 : strings) {

                        String result = null;
                        String result1 = null;

                        result = "((" + integer[0] + str1 + integer[1] + ")";

                        result += str2 + integer[2] + ")";

                        result += str3 + integer[3];


                        try {
                            Object aDouble = getDouble(result);
                            if (aDouble.toString().equals("24")) {
                                System.out.println(result);
                            }
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }


                        //(a+b)*(c+d) ac ad bc bd
                        result = "(" + integer[0] + str1 + integer[1] + ")" + str2 + "(" + integer[2] + str3 + integer[3] + ")";
                        try {
                            Object aDouble = getDouble(result);
                            if (aDouble.toString().equals("24")) {
                                System.out.println(result);
                            }
                        } catch (ScriptException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }


    }


    /**
     * 进行不同的排列组合,通过index指向当前的数，用i循环代表可以进行交换的数
     *
     * @return
     */
    public void getDiff(List<Integer[]> allIds, Integer[] ids, Integer index) {
        if (ids == null || ids.length == 0 || index < 0 || index >= ids.length) {
            System.out.println("数据错误");
            return;
        }

        //如果index和数组长度相等代表指向了最后一个数了
        if (index == ids.length - 1) {
            Integer[] clone = ids.clone();
            allIds.add(clone);
            return;

        }


        //让id[index]和id[i]进行交换
        for (int i = index; i < ids.length; i++) {
            ids = swap(ids, index, i);
            getDiff(allIds, ids, index + 1);
            //交换回来，以便进行新的交换
            ids = swap(ids, index, i);


        }
        /**
         * 1234 1243 1324 1342 1423 1432
         */
        //首先先把最后的两位进行交换，


    }


    /**
     * 进行交换
     *
     * @param a
     * @param b
     * @return
     */
    public Integer[] swap(Integer[] ids, int a, int b) {
        Integer id = ids[a];
        ids[a] = ids[b];
        ids[b] = id;
        return ids;

    }


    public Object getDouble(String str) throws ScriptException {
      /*  char[] chars={'1','2','3','4'};

        p(chars,0);*/
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine js = scriptEngineManager.getEngineByName("js");
        Object eval = js.eval(str);

        return eval;
    }

    @Test
    public void test9() {
        ArrayList<Integer[]> integers = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        //完成四选2
        get4to2test1(integers, new Integer[]{1, 2, 3, 4}, 2, 0, stack);

    }

    private void get4to2test1(List<Integer[]> lists, Integer[] integers, Integer num, Integer index, Stack<Integer> stack) {

        if (integers == null || integers.length == 0 || num == 0) {
            return;
        }
        if (stack.size()==num) {
            System.out.println(stack);
            return;
        }
        for (int i = index; i < integers.length; i++) {

                stack.add(integers[i]);
                get4to2test1(lists, integers, num, i + 1, stack);
                stack.pop();

        }
    }
    @Test
    public void test10() {
        ArrayList<Integer[]> integers = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        //完成四选2
        get4to2test2(integers, new Integer[]{1, 2, 3, 4}, 2,  stack);

    }
    private void get4to2test2(List<Integer[]> lists, Integer[] integers, Integer index, Stack<Integer> stack) {

        if (integers == null || integers.length == 0 ) {
            return;
        }
        if (stack.size()==integers.length) {
            System.out.println(stack);
            return;
        }
        for (int i = 0; i < integers.length; i++) {
            if (!stack.contains(integers[i])) {
                stack.add(integers[i]);
                get4to2test2(lists, integers,i + 1, stack);
                stack.pop();
            }
        }
    }
}














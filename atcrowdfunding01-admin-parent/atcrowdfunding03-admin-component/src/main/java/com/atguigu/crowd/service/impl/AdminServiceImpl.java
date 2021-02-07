package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrownConstant;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.po.AdminExample;
import com.atguigu.crowd.service.api.AdminService;


import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.util.List;




@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);


        }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin loginAdmin(String loginAcct,String password) throws FailedLoginException {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins == null || admins.size() == 0) {
            throw new FailedLoginException(CrownConstant.MESSAGE_STRING_INVALIDATE);
        }
        //用于比较和用username查询出来的password是否相等
        String mp5ForForm = CrowdUtil.getMp5(password);
        if (admins.size() == 1) {
            Admin admin = admins.get(0);
            String userPswd = admin.getUserPswd();

            if (!userPswd.equals(mp5ForForm)) {
                throw new FailedLoginException(CrownConstant.MESSAGE_STRING_INVALIDATE);
            }
            return admin;
        }
      throw  new RuntimeException();


    }

    @Override
    public PageInfo<Admin> getPageInfoList(String keyCard,String pageNum,String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Admin> byFuzzyQuery = adminMapper.findByFuzzyQuery(keyCard);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(byFuzzyQuery);
        return adminPageInfo;

    }

    @Override
    public void deleteAdmin(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin getAdminById(Integer id) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() == 1) {
            return admins.get(0);
        }
        return null;
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }


}

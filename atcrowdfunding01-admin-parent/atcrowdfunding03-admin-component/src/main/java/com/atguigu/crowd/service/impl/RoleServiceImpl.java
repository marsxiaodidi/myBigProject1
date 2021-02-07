package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.po.Role;
import com.atguigu.crowd.po.RoleExample;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 10185
 * @create 2021/2/5 10:41
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;


    @Override
    public PageInfo<Role> getPageInfo(String pageNum, String pageSize, String keyCard) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Role> rolesByKeyCard = roleMapper.getRolesByKeyCard(keyCard);

        return new PageInfo<>(rolesByKeyCard);

    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void deleteRolesByIds(Integer... roleIds) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(Arrays.asList(roleIds));
        roleMapper.deleteByExample(roleExample);
    }
}

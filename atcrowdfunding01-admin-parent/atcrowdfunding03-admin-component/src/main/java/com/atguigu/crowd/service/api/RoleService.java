package com.atguigu.crowd.service.api;

import com.atguigu.crowd.po.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 10185
 * @create 2021/2/5 10:41
 */
public interface RoleService {
    /**
     * 获取分页后的Role集合
     * @param pageNum
     * @param pageSize
     * @param keyCard
     * @return
     */
    PageInfo<Role> getPageInfo(String pageNum, String pageSize, String keyCard);

    /**
     * 加入role
     * @param role
     */
    void addRole(Role role);

    /**
     * 更新role
     * @param role
     */
    void updateRole(Role role);

    /**
     * 单个删除
     * @param id
     */
    void deleteRole(Integer id);

    /**
     * 删除多个id
     * @param roleIds
     */
    void deleteRolesByIds(Integer... roleIds);

    /**
     * 得到已经该id已经分配的role集合
     * @param id
     * @return
     */
    List<Role> getAlreadyAssignRole(Integer id);

    /**
     * 得到该id未进行分配的role集合
     * @param id
     * @return
     */
    List<Role> getNotAssignRole(Integer id);
}

package com.atguigu.crowd.service.api;

import com.atguigu.crowd.po.Role;
import com.github.pagehelper.PageInfo;

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
}

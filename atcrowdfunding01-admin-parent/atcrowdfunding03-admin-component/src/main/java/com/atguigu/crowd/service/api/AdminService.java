package com.atguigu.crowd.service.api;

import com.atguigu.crowd.po.Admin;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.util.List;

/**
 * admin类
 * @author 10185
 * @date 2021/2/2 18:37
 *
 */
public interface AdminService {


    /**
     * 保存管理员信息
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 得到所有管理员信息
     * @return
     */
    List<Admin> getAll();

    /**
     * 用于检测管理员信息
     * @param username
     * @param password
     */
    Admin loginAdmin(String username,String password) throws FailedLoginException;

    /**
     * 通过pageHelper方法拿出分页后的数据
     * @return
     */
    PageInfo<Admin> getPageInfoList(String keyCard, String pageNum, String pageSize);

    void deleteAdmin(Integer id);

    /**
     * 通过id获取admin
     * @param id
     * @return
     */
    Admin getAdminById(Integer id);

    /**
     * 通过admin修改
     * @param admin
     */
    void updateAdmin(Admin admin);
}

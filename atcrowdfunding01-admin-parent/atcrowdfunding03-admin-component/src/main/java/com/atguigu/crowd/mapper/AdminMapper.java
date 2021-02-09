package com.atguigu.crowd.mapper;

import com.atguigu.crowd.po.Admin;
import com.atguigu.crowd.po.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 通过关键词找出对应的admin人员
     * @param keyCard
     * @return
     */
    List<Admin> findByFuzzyQuery(String keyCard);

    void deleteAdminIdFromInner(Integer id);

    void addRoleFromInner(@Param("adminId")Integer adminId,@Param("ids")Integer[] ids);
}
package com.atguigu.crowd.mapper;

import com.atguigu.crowd.po.Role;
import com.atguigu.crowd.po.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> getRolesByKeyCard(String keyCard);

    /**
     * 得到该id已经分配的Role
     * @param id
     * @return
     */
    List<Role> getAlreadyAssignRole(Integer id);

    /**
     * 得到没有进行分配的ROle
     * @param id
     * @return
     */
    List<Role> getNotAssignRole(Integer id);

}
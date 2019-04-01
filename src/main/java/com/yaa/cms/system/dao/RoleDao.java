package com.yaa.cms.system.dao;

import com.yaa.cms.system.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {

    List<SysRole> selectRoleList(Map<String, Object> map);

    SysRole selectRoleByID(Integer id);

    int saveRole(SysRole role);

    int updateRole(SysRole role);

    int removeRole(Integer id);

    int batchRemoveRole(Integer[] ids);

}

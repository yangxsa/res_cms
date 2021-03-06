package com.yaa.cms.system.dao;

import com.yaa.cms.system.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuDao {

    int removeRoleMenuByRoleId(Integer roleId);

    int batchSaveRoleMenu(List<SysRoleMenu> roleMenus);

    List<Integer> listMenuIdByRoleId(Integer roleId);

}

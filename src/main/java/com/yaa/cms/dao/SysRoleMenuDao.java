package com.yaa.cms.dao;

import com.yaa.cms.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {

    int removeRoleMenuByRoleId(Integer roleId);

    int batchSaveRoleMenu(List<SysRoleMenu> roleMenus);

    List<Integer> listMenuIdByRoleId(Integer roleId);

}

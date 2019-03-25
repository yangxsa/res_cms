package com.yaa.cms.service;


import com.yaa.cms.model.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

   List<SysRole> selectRoleList(Map<String,Object> params);

   SysRole selectRoleByID(Integer id);

   int saveRole(SysRole role);

   int updateRole(SysRole role);

   int removeRole(Integer id);

   int batchRemoveRole(Integer[] ids);

   List<SysRole> selectRoleByUserID(Integer userID);

}

package com.yaa.cms.dao;

import com.yaa.cms.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<Integer> selectRoleIdList(Integer userId);

    int removeByUserID(Integer userId);

    int batchSave(List<SysUserRole> list);

    int batchRemoveByUserID(Integer[] ids);

    int removeByUserId(Integer userId);

    List<Integer> listRoleId(Integer userId);


}

package com.yaa.cms.system.dao;

import com.yaa.cms.system.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserDao {

    SysUser selectUserByUserName(String username);

    List<SysUser> selectUserList(Map<String, Object> param);

    int countUserRecords(Map<String, Object> param);

    SysUser selectUserById(Integer id);

    int updateUserBySelective(SysUser user);

    int saveUser(SysUser user);

    int batchRemoveUser(Integer[] userIds);

    int removeUser(Integer userId);


}

package com.yaa.cms.service;



import com.yaa.cms.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {

    SysUser selectUserByUsername(String username);

    int countTotalUserRecord(Map<String,Object> params);

    List<SysUser> selectUserList(Map<String, Object> params);

    int countUserRecords(Map<String, Object> params);

    SysUser selectUserById(Integer id);

    int updateUserByRecord(SysUser user);

    int saveUser(SysUser user);

    int batchRemoveUser(Integer[] userIds);

    int remove(Integer id);

    //表单验证
    boolean exit(Map<String, Object> params);

    int resetPassword(SysUser user, SysUser loginUser) throws Exception;

    int adminResetPwd(SysUser user) throws Exception;

}

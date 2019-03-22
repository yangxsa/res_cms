package com.yaa.cms.service.impl;

import com.yaa.cms.dao.SysRoleDao;
import com.yaa.cms.dao.SysUserDao;
import com.yaa.cms.dao.SysUserRoleDao;
import com.yaa.cms.model.SysRole;
import com.yaa.cms.model.SysUser;
import com.yaa.cms.model.SysUserRole;
import com.yaa.cms.service.SysUserService;
import com.yaa.cms.util.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysUserRoleDao userRoleDao;
    @Autowired
    private SysRoleDao roleDao;

    @Override
    public SysUser selectUserByUsername(String username) {
        if(StringUtils.isNotBlank(username)){
            return userDao.selectUserByUserName(username);
        }
        return null;
    }

    @Override
    public int countTotalUserRecord(Map<String, Object> params) {
        return userDao.countUserRecords(params);
    }

    @Override
    public List<SysUser> selectUserList(Map<String, Object> params,int startIndex,int endIndex) {
        params.put("startIndex",startIndex);
        params.put("endIndex",endIndex);
        List<SysUser> users = userDao.selectUserList(params);
        for (SysUser user : users) {
            List<Integer> roleIds = userRoleDao.selectRoleIdList(user.getId());
            if (roleIds != null && roleIds.size() > 0) {
                String roleNames = null;
                for (Integer roleId : roleIds) {
                    SysRole role = roleDao.selectRoleByID(roleId);
                    if(roleNames==null){
                        roleNames = role.getName();
                    }else{
                        roleNames = roleNames + "," + role.getName();
                    }
                }
                user.setRoleNames(roleNames);
            }
            user.setRoleIds(roleIds);
        }
        return users;
    }

    @Override
    public int countUserRecords(Map<String, Object> params) {
        return userDao.countUserRecords(params);
    }

    @Override
    public SysUser selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    @Override
    public int updateUserByRecord(SysUser user) {
        int r = userDao.updateUserBySelective(user);
        Integer userId = user.getId();
        List<Integer> roles = user.getRoleIds();
        userRoleDao.removeByUserID(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleDao.batchSave(list);
        }
        return r;
    }

    @Override
    public int saveUser(SysUser user) {
        int count = userDao.saveUser(user);
        Integer userId = user.getId();
        List<Integer> roles = user.getRoleIds();
        userRoleDao.removeByUserID(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (Integer roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleDao.batchSave(list);
        }
        return count;
    }

    @Transactional
    @Override
    public int batchRemoveUser(Integer[] userIds) {
        int count = userDao.batchRemoveUser(userIds);
        userRoleDao.batchRemoveByUserID(userIds);
        return count;
    }

    @Override
    public int remove(Integer id) {
        userRoleDao.removeByUserId(id);
        return userDao.removeUser(id);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        return userDao.selectUserList(params).size() > 0;
    }

    @Override
    public int resetPassword(SysUser user,SysUser loginUser) throws Exception {
        if (Objects.equals(user.getId(), loginUser.getId())) {
            if (Objects.equals(Md5Utils.encrypt(user.getPassword(), loginUser.getSalt()), loginUser.getPassword())) {
                user.setPassword(Md5Utils.encrypt(user.getPassword(), loginUser.getSalt()));
                return userDao.updateUserBySelective(user);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(SysUser user) throws Exception {
        SysUser updateUser = userDao.selectUserById(user.getId());
        if ("admin".equals(updateUser.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        updateUser.setPassword(Md5Utils.encrypt(user.getPassword(), updateUser.getSalt()));
        return userDao.updateUserBySelective(updateUser);
    }


}

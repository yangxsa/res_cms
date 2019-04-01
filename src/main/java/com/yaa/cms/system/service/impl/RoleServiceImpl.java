package com.yaa.cms.system.service.impl;

import com.yaa.cms.system.dao.RoleDao;
import com.yaa.cms.system.dao.RoleMenuDao;
import com.yaa.cms.system.dao.UserRoleDao;
import com.yaa.cms.system.model.SysRole;
import com.yaa.cms.system.model.SysRoleMenu;
import com.yaa.cms.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<SysRole> selectRoleList(Map<String,Object> params) {
        return roleDao.selectRoleList(params);
    }

    @Override
    public SysRole selectRoleByID(Integer id) {
        return roleDao.selectRoleByID(id);
    }

    @Override
    public int saveRole(SysRole role) {
        int r = roleDao.saveRole(role);
        List<Integer> menuIds = role.getMenuIds();
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(role.getId());
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuDao.batchSaveRoleMenu(rms);
        }
        return r;
    }

    @Override
    public int updateRole(SysRole role) {
        int r = roleDao.updateRole(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getId();
        roleMenuDao.removeRoleMenuByRoleId(roleId);
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuDao.batchSaveRoleMenu(rms);
        }
        return r;
    }

    @Override
    public int removeRole(Integer id) {
        return roleDao.removeRole(id);
    }

    @Override
    public int batchRemoveRole(Integer[] ids) {
        return roleDao.batchRemoveRole(ids);
    }

    @Override
    public List<SysRole> selectRoleByUserID(Integer userID) {
        List<Integer> rolesIds = userRoleDao.selectRoleIdList(userID);
        List<SysRole> roles = roleDao.selectRoleList(new HashMap<String,Object>(16));
        for (SysRole role : roles) {
            role.setSign("false");
            for (Integer roleId : rolesIds) {
                if (Objects.equals(role.getId(), roleId)) {
                    role.setSign("true");
                    break;
                }
            }
        }
        return roles;
    }
}

package com.yaa.cms.service.impl;

import com.yaa.cms.dao.SysRoleDao;
import com.yaa.cms.dao.SysRoleMenuDao;
import com.yaa.cms.dao.SysUserRoleDao;
import com.yaa.cms.model.SysRole;
import com.yaa.cms.model.SysRoleMenu;
import com.yaa.cms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysRoleMenuDao roleMenuDao;
    @Autowired
    private SysUserRoleDao userRoleDao;

    @Override
    public List<SysRole> selectRoleList() {
        return roleDao.selectRoleList(new HashMap<String,Object>(16));
    }

    @Override
    public SysRole selectRoleByID(Integer id) {
        return roleDao.selectRoleByID(id);
    }

    @Override
    public int saveRole(SysRole role) {
        return roleDao.saveRole(role);
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

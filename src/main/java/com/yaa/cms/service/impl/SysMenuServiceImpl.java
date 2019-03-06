package com.yaa.cms.service.impl;

import com.yaa.cms.dao.SysMenuDao;
import com.yaa.cms.dao.SysRoleMenuDao;
import com.yaa.cms.model.SysMenu;
import com.yaa.cms.service.SysMenuService;
import com.yaa.cms.util.BuildTree;
import com.yaa.cms.vo.Tree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao menuDao;
    @Autowired
    private SysRoleMenuDao roleMenuDao;

    @Override
    public Set<String> getUserPermsByID(Integer id) {
        List<String> perms = menuDao.selectUsersPerms(id);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<SysMenu>> selectMenuTreeByID(Integer id,String url) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = menuDao.selectMenuTreeByID(id);
        for (SysMenu menu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            //判断当前路径
            if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(menu.getUrl()) && url.contains(menu.getUrl())){
                tree.setChecked(true);
            }
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public List<SysMenu> selectAllMenu(Map<String, Object> params) {
        return menuDao.selectMenu(params);
    }

    @Override
    public SysMenu selectMenuByID(Integer id) {
        return menuDao.selectMenuByID(id);
    }

    @Override
    public int insertMenu(SysMenu sysMenu) {
        return menuDao.insertMenu(sysMenu);
    }

    @Override
    public int updateMenuByID(SysMenu sysMenu) {
        return menuDao.updateMenuByID(sysMenu);
    }

    @Override
    public Tree<SysMenu> selectMenuTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = menuDao.selectMenu(new HashMap<String,Object>(16));
        for (SysMenu menu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> selectMenuTree(Integer roleID) {
        // 根据roleId查询权限
        List<SysMenu> menus = menuDao.selectMenu(new HashMap<String, Object>(16));
        List<Integer> menuIds = roleMenuDao.listMenuIdByRoleId(roleID);
        List<Integer> temp = menuIds;
        for (SysMenu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menuDOs = menuDao.selectMenu(new HashMap<String, Object>(16));
        for (SysMenu sysMenuDO : menuDOs) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Integer menuId = sysMenuDO.getId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int removeMenuByID(Integer id) {
        return menuDao.removeMenuByID(id);
    }

}

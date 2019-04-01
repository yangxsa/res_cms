package com.yaa.cms.system.service;


import com.yaa.cms.system.model.SysMenu;
import com.yaa.cms.system.model.vo.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MenuService {

    Set<String> getUserPermsByID(Integer id);

    List<Tree<SysMenu>> selectMenuTreeByID(Integer id,String url);

    List<SysMenu> selectAllMenu(Map<String, Object> params);

    SysMenu selectMenuByID(Integer id);

    int insertMenu(SysMenu sysMenu);

    int updateMenuByID(SysMenu sysMenu);

    Tree<SysMenu> selectMenuTree();

    Tree<SysMenu> selectMenuTree(Integer roleID);

    int removeMenuByID(Integer id);

}

package com.yaa.cms.controller.base;


import com.yaa.cms.model.SysMenu;
import com.yaa.cms.service.SysMenuService;
import com.yaa.cms.util.PageUtil;
import com.yaa.cms.util.ShiroUtils;
import com.yaa.cms.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class BaseController {


    @Autowired
    private SysMenuService menuService;


    /**
     * 跳转页面
     * @param request
     * @param viewName
     * @return
     */
    protected String render(HttpServletRequest request,String viewName) {
        List<Tree<SysMenu>> menus = menuService.selectMenuTreeByID(ShiroUtils.getUserId(),request.getRequestURI());
        request.setAttribute("menus", menus);
        request.setAttribute("name", ShiroUtils.getUser().getName());
        if(false){
            //TODO 用户头像显示
            request.setAttribute("picUrl","");
        }else {
            request.setAttribute("picUrl","/img/photo_s.jpg");
        }
        request.setAttribute("username", ShiroUtils.getUser().getUsername());
        return viewName;
    }

    /**
     * 分页组件
     * @param page
     * @param totalSize
     * @param request
     */
    protected void setPageNavigation(int page, int totalSize,HttpServletRequest request) {
        PageUtil pageInfo = new PageUtil(page,totalSize);
        request.setAttribute("pageInfo",pageInfo);
    }


}

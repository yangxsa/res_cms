package com.yaa.cms.controller.base;


import com.yaa.cms.model.SysMenu;
import com.yaa.cms.service.SysMenuService;
import com.yaa.cms.util.PageUtil;
import com.yaa.cms.util.ShiroUtils;
import com.yaa.cms.vo.Tree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {


    @Autowired
    public HttpServletRequest request;
    @Autowired
    private SysMenuService menuService;

    /**
     * 跳转页面
     * @param viewName
     * @return
     */
    protected String render(String viewName) {
        List<Tree<SysMenu>> menus = menuService.selectMenuTreeByID(ShiroUtils.getUserId(),request.getRequestURI());
        request.setAttribute("menus", menus);
        request.setAttribute("cmsName", ShiroUtils.getUser().getName());
        if(false){
            //TODO 用户头像显示
            request.setAttribute("picUrl","");
        }else {
            request.setAttribute("picUrl","/img/photo_s.jpg");
        }
        request.setAttribute("cmsUserName", ShiroUtils.getUser().getUsername());
        return viewName;
    }

    /**
     * 分页组件
     * @param page
     * @param totalSize
     */
    protected void setPageNavigation(int page, int totalSize) {
        PageUtil pageInfo = new PageUtil(page,totalSize);
        request.setAttribute("pageInfo",pageInfo);
    }

    /**
     * 组装参数
     */
    protected Map<String,Object> buildParam(){
        Map<String,Object> params = new HashMap<>();
        Enumeration em = request.getParameterNames();
         while (em.hasMoreElements()) {
              String name = (String) em.nextElement();
            String value = request.getParameter(name);
            if(StringUtils.isNotBlank(value)){
                params.put(name,value);
                request.setAttribute(name,value);
            }
        }
        return params;
    }


}

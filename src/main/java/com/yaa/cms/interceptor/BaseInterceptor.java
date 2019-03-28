package com.yaa.cms.interceptor;

import com.yaa.cms.model.SysMenu;
import com.yaa.cms.model.SysUser;
import com.yaa.cms.service.MenuService;
import com.yaa.cms.util.ShiroUtils;
import com.yaa.cms.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 自定义拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    @Autowired
    private MenuService menuService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(!request.getRequestURI().contains("error")) {
            SysUser user = ShiroUtils.getUser();
            if (user != null) {
                List<Tree<SysMenu>> menus = menuService.selectMenuTreeByID(ShiroUtils.getUserId(), request.getRequestURI());
                request.setAttribute("menus", menus);
                request.setAttribute("cmsName", ShiroUtils.getUser().getName());
                if (false) {
                    //TODO 用户头像显示
                    request.setAttribute("picUrl", "");
                } else {
                    request.setAttribute("picUrl", "/img/photo_s.jpg");
                }
                request.setAttribute("cmsUserName", ShiroUtils.getUser().getUsername());
                request.setAttribute("cmsUserId", ShiroUtils.getUserId());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

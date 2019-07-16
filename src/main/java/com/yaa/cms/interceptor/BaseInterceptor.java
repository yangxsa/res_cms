package com.yaa.cms.interceptor;

import com.yaa.cms.system.model.SysMenu;
import com.yaa.cms.system.model.SysUser;
import com.yaa.cms.system.service.MenuService;
import com.yaa.cms.util.HeadUtils;
import com.yaa.cms.util.ShiroUtils;
import com.yaa.cms.system.model.vo.Tree;
import org.apache.commons.lang3.StringUtils;
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
        SysUser user = ShiroUtils.getUser();
        if (user != null) {
            List<Tree<SysMenu>> menus = menuService.selectMenuTreeByID(ShiroUtils.getUserId(), request.getRequestURI());
            request.setAttribute("menus", menus);
            request.setAttribute("cmsName", ShiroUtils.getUser().getName());
            if(StringUtils.isNotBlank(user.getEmail())){
                request.setAttribute("picUrl", HeadUtils.gravatar(user.getEmail()));
            }else{
                request.setAttribute("picUrl", HeadUtils.gravatar("123@163.com"));
            }
            request.setAttribute("cmsName",user.getName());
            request.setAttribute("cmsUserId",user.getId());
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

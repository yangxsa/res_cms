package com.yaa.cms.controller.base;


import com.yaa.cms.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class BaseController {


    @Autowired
    public HttpServletRequest request;



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

    /**
     * 组装参数
     * @param page
     * @return
     */
    protected Map<String,Object> buildPageParam(int page){
        Map<String,Object> params = this.buildParam();
        if(page > 0){
            int offset = PageUtil.getOffset(page);
            params.put("offset",offset);
            params.put("limit",PageUtil.getLimit());
        }
        return params;
    }


}

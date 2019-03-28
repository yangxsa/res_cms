package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysNotify;
import com.yaa.cms.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/notify")
public class NotifyController extends BaseController {

    @Autowired
    private NotifyService notifyService;

    String prefix = "system/notify";

    /**
     * 消息列表
     * @return
     */
    @GetMapping("")
    public String list(@RequestParam(value = "page",defaultValue = "1")int page) {
        Map<String,Object> params = buildPageParam(page);
        // 查询列表数据
        int total = notifyService.count(params);
        List<SysNotify> taskScheduleJobList = notifyService.list(params);
        this.setPageNavigation(page,total);
        request.setAttribute("jobs",taskScheduleJobList);
        return prefix + "/notify";
    }


}

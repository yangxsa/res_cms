package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;

import java.util.List;
import java.util.Map;

import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yaa.cms.model.Notify;
import com.yaa.cms.service.NotifyService;


@Controller
@RequestMapping("/system/notify")
public class NotifyController extends BaseController {

    String prefix = "system/notify";

    @Autowired
    private NotifyService notifyService;


    @GetMapping(value = "")
    @RequiresPermissions("system:notify:notify")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page) {
        Map<String, Object> param = buildPageParam(page);
        //查询列表数据
        int total = notifyService.countNotifyRecord(param);
        List<Notify> notifyList = notifyService.selectNotifyByPage(param);
        this.setPageNavigation(page, total);
        request.setAttribute("notifyList", notifyList);
        return prefix + "/notify";
    }

    @GetMapping("/add")
    @RequiresPermissions("system:notify:add")
    public String add() {
        return "system/notify/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:notify:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
		Notify notify =notifyService.selectNotifyByID(id);
        model.addAttribute("notify", notify);
        return "system/notify/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:notify:add")
    public Result save( Notify notify) {
        if (notifyService.saveNotify(notify) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:notify:edit")
    public Result update( Notify notify) {
			notifyService.updateNotify(notify);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:notify:remove")
    public Result remove( Integer id) {
        if (notifyService.removeNotify(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}

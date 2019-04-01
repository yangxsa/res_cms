package com.yaa.cms.system.controller;

import com.yaa.cms.controller.base.BaseController;

import java.util.List;
import java.util.Map;

import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yaa.cms.system.model.Notify;
import com.yaa.cms.system.service.NotifyService;


@Controller
@RequestMapping("/sys/notify")
public class NotifyController extends BaseController {

    String prefix = "system/notify";

    @Autowired
    private NotifyService notifyService;


    @GetMapping(value = "")
    @RequiresPermissions("sys:notify:notify")
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
    @RequiresPermissions("sys:notify:add")
    public String add() {
        return "sys/notify/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:notify:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
		Notify notify =notifyService.selectNotifyByID(id);
        model.addAttribute("notify", notify);
        return "sys/notify/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:notify:add")
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
    @RequiresPermissions("sys:notify:edit")
    public Result update( Notify notify) {
			notifyService.updateNotify(notify);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("sys:notify:remove")
    public Result remove( Integer id) {
        if (notifyService.removeNotifyByID(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}

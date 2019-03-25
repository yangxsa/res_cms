package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysRole;
import com.yaa.cms.service.SysRoleService;
import com.yaa.cms.util.PageUtil;
import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends BaseController {

    String prefix = "system/role";

    @Autowired
    SysRoleService roleService;


    @GetMapping("")
    @RequiresPermissions("sys:role:role")
    public String list() {
        Map<String,Object> param = buildParam();
        List<SysRole> roles = roleService.selectRoleList(param);
        request.setAttribute("roles",roles);
        return render(prefix + "/role");
    }

    @GetMapping("/add")
    @RequiresPermissions("sys:role:add")
    public String add() {
        return prefix + "/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("sys:role:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        SysRole role = roleService.selectRoleByID(id);
        model.addAttribute("role", role);
        return prefix + "/edit";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:role:add")
    public Result save(SysRole role) {
        if (roleService.saveRole(role) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("sys:role:edit")
    public Result update(SysRole role) {
        if (roleService.updateRole(role) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("sys:role:remove")
    public Result save(Integer id) {
        if (roleService.removeRole(id) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "删除失败");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    @RequiresPermissions("sys:role:batchRemove")
    public Result batchRemove(@RequestParam("ids[]") Integer[] ids) {
        int r = roleService.batchRemoveRole(ids);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}

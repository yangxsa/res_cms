package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysMenu;
import com.yaa.cms.service.SysMenuService;
import com.yaa.cms.util.Result;
import com.yaa.cms.vo.Tree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{

    String prefix = "system/menu";

    @Autowired
    SysMenuService menuService;

    @GetMapping
    @RequiresPermissions("sys:menu:menu")
    public String menu() {
        return render(prefix + "/menu");
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    @RequiresPermissions("sys:menu:menu")
    public List<SysMenu> list(@RequestParam Map<String, Object> params) {
        List<SysMenu> menus = menuService.selectAllMenu(params);
        return menus;
    }

    @GetMapping(value = "/add/{pId}")
    @RequiresPermissions("sys:menu:add")
    public String add(Model model, @PathVariable("pId") Integer pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectMenuByID(pId).getName());
        }
        return prefix + "/add";
    }

    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:menu:edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        SysMenu menu = menuService.selectMenuByID(id);
        Integer pId = menu.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.selectMenuByID(pId).getName());
        }
        model.addAttribute("menu", menu);
        return prefix+"/edit";
    }

    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:menu:add")
    public Result save(SysMenu menu) {
        if (menuService.insertMenu(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("sys:menu:edit")
    public Result update(SysMenu menu) {
        if (menuService.updateMenuByID(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "更新失败");
        }
    }

    @ResponseBody
    @GetMapping(value = "/tree")
    public Tree<SysMenu> tree() {
        Tree<SysMenu>  tree = menuService.selectMenuTree();
        return tree;
    }

    @ResponseBody
    @GetMapping(value = "/tree/{roleId}")
    public Tree<SysMenu> tree(@PathVariable("roleId") Integer roleId) {
        Tree<SysMenu> tree = menuService.selectMenuTree(roleId);
        return tree;
    }


    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("sys:menu:remove")
    public Result remove(Integer id) {
        if (menuService.removeMenuByID(id) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "删除失败");
        }
    }

}

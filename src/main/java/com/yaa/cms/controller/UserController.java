package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysRole;
import com.yaa.cms.model.SysUser;
import com.yaa.cms.service.SysRoleService;
import com.yaa.cms.service.SysUserService;
import com.yaa.cms.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yaa.cms.util.ShiroUtils.getUserId;
import static com.yaa.cms.util.ShiroUtils.getUser;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController extends BaseController {

    private String prefix="system/user"  ;

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;


    @GetMapping("")
    @RequiresPermissions("sys:user:user")
    public String user(@RequestParam(value = "page",defaultValue = "1")int page,SysUser user,HttpServletRequest request) {
        Map<String,Object> params = new HashMap<>();
        if(StringUtils.isNotBlank(user.getName())){
            params.put("name",user.getName());
        }
        int totalRecord = userService.countTotalUserRecord(params);
        int startIndex = PageUtil.getStartIndex(page);
        int endIndex = PageUtil.getEndIndex(page);
        List<SysUser> list = userService.selectUserList(params,startIndex,endIndex);
        this.setPageNavigation(page,totalRecord,request);
        request.setAttribute("list",list);
        return render(request,prefix + "/user");
    }


    @GetMapping("/add")
    @RequiresPermissions("sys:user:add")
    String add(Model model) {
        List<SysRole> roles = roleService.selectRoleList();
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("sys:user:edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        SysUser user = userService.selectUserById(id);
        model.addAttribute("user", user);
        List<SysRole> roles = roleService.selectRoleByUserID(id);
        model.addAttribute("roles", roles);
        return prefix+"/edit";
    }

    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("sys:role:edit")
    public Result update(SysUser user) {
        if (userService.updateUserByRecord(user) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("sys:user:add")
    public Result save(SysUser user) {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        Md5Utils.encrypt(user);
        if (userService.saveUser(user) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    @RequiresPermissions("sys:user:batchRemove")
    public Result batchRemove(@RequestParam("ids[]") Integer[] userIds) {
        int r = userService.batchRemoveUser(userIds);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }



    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("sys:user:remove")
    public Result remove(Integer id) {
        if (userService.remove(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }


    @GetMapping("/resetPwd/{id}")
    @RequiresPermissions("sys:user:resetPwd")
    public String resetPwd(@PathVariable("id") Integer id, Model model) {
        SysUser user = new SysUser();
        user.setId(id);
        model.addAttribute("user", user);
        return prefix + "/reset_pwd";
    }

    @ResponseBody
    @PostMapping("/resetPwd")
    public Result resetPwd(SysUser user) {
        try{
            userService.resetPassword(user, getUser());
            return Result.ok();
        }catch (Exception e){
            return Result.error(1,e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/adminResetPwd")
    @RequiresPermissions("sys:user:resetPwd")
    public Result adminResetPwd(SysUser user) {
        try{
            userService.adminResetPwd(user);
            return Result.ok();
        }catch (Exception e){
            return Result.error(1,e.getMessage());
        }

    }

    @ResponseBody
    @PostMapping("/exit")
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }

    @GetMapping("/personal")
    public String personal(Model model) {
        SysUser user  = userService.selectUserById(getUserId());
        model.addAttribute("user",user);
        return prefix + "/personal";
    }

}

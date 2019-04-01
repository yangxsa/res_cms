package com.yaa.cms.system.controller;


import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.system.model.SysUser;
import com.yaa.cms.system.service.UserService;
import com.yaa.cms.util.AlgorithmUtil;
import com.yaa.cms.util.Result;
import com.yaa.cms.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/main")
    public String main(){
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(String username, String password){
        SysUser user = userService.selectUserByUsername(username);
        if(user == null){
            return Result.error("用户或密码错误");
        }
        password = AlgorithmUtil.encrypt(password,user.getSalt());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,user.getSalt());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return Result.ok();
        } catch (AuthenticationException e) {
            return Result.error("用户或密码错误");
        }
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

}

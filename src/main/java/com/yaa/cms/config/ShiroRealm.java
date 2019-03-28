package com.yaa.cms.config;

import com.yaa.cms.model.SysUser;
import com.yaa.cms.service.MenuService;
import com.yaa.cms.service.UserService;
import com.yaa.cms.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        MenuService menuService = ContextUtil.getBean(MenuService.class);
        Integer userId = ShiroUtils.getUserId();
        Set<String> perms = menuService.getUserPermsByID(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }


    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserService userService = ContextUtil.getBean(UserService.class);
        String username = (String) token.getPrincipal();
        SysUser user = userService.selectUserByUsername(username);
        if(user == null){
            throw new UnknownAccountException("账号或密码不正确");
        }
        if (user.getStatus() == 0) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;

    }
}

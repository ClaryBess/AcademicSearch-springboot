package com.example.demo.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户信息
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //执行授权逻辑
        info.addStringPermission("user:add");
        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken：这个就是前面subject.login()传递过来的token
     * @return 认证不通过就返回null
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //执行认证的过程
        //从数据库拿出账号密码和用户传递过来的账号密码对比
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        //数据查询账号密码
        if (!username.equals("数据库账号")) {
            return null;//返回为空，shiro会抛出UnKnowAccountException（也就是账号不存在）
        }
        //密码不需要判断，直接返回一个AuthenticationInfo的实现类，把数据库密码做参数，shiro会自己判断的
        //shiro内置的加密算法
        ByteSource credentialsSalt = ByteSource.Util.bytes("user.getUsername" + "user.getSalt()");//从数据库查询的
        return new SimpleAuthenticationInfo(username, "数据库加密之后的密码", credentialsSalt, this.getName());
    }
}

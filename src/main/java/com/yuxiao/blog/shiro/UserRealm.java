package com.yuxiao.blog.shiro;

import com.yuxiao.blog.bean.User;
import com.yuxiao.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ${邹} on 2018/11/12.
 * 描述：自定义realm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /*
    *
    * 执行授权逻辑
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println( "执行授权逻辑" );
        // 给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 给资源添加授权字符串
        //info.addStringPermission( "user:add" );

        // 到数据库查询当前登录用户的授权字符串，获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User dbUser = userService.findById( user.getuId());
        info.addStringPermission( dbUser.getPerms());
        return info;
    }

    /*
    *
    * 执行认证逻辑
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println( "执行认证逻辑" );
        // 编写shiro 判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userService.findByUsername( token.getUsername() );
        if (user == null){
            return null;
        }
        // 判断密码
        return new SimpleAuthenticationInfo( user,user.getPassword(),"" );
    }
}

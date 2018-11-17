package com.yuxiao.blog.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ${邹} on 2018/11/12.
 * 描述：shiro配置类
 */
@Configuration
public class ShiroConfig {

    /*
    *创建ShiroFilterFactoryBean
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager( securityManager );
        /*
        * 添加shiro内置过滤器，可以实现权限相关的拦截器
        *   常用过滤器：
        *           anon ： 无需认证（登录） 可以访问
        *           authc ： 必须认证才可以访问
        *           perms ： 该资源必须得到资源权限才可以访问
        *           role ： 该资源必须得到角色权限才可以访问
        *
        * */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
//        filterMap.put( "/shirotest/add","authc" );
        filterMap.put( "/shirotest/shiro_test","anon" ); // 无需验证放行
        filterMap.put( "/api/user/login","anon" );
        filterMap.put( "/api/user/reg","anon" );
        filterMap.put( "/shirotest/role","anon" );
        // 授权过滤器
        filterMap.put( "/shirotest/add","perms[user:add]" );
        filterMap.put( "/shirotest/update","perms[user:update]" );
        filterMap.put( "/document","perms[admin:admin]");
        filterMap.put( "//api/contents/writeBlog/{id}","perms[user:add]");
        filterMap.put( "//api/contents/writeBlog/{id}","perms[admin:admin]");
        filterMap.put( "/shirotest/**","authc" );
        shiroFilterFactoryBean.setUnauthorizedUrl( "/shirotest/role" ); // 授权跳转页面
        shiroFilterFactoryBean.setLoginUrl( "/login" ); // 跳转页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap( filterMap );
        return shiroFilterFactoryBean;
    }


    /*
    * 创建DefaultWebSecurityManager 安全管理器
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm( userRealm );
        return securityManager;
    }


    // 创建Realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    // 配置shiroDialect ,用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}

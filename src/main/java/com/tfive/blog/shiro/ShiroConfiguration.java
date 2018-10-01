package com.tfive.blog.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Copyright:
 * @Desc:
 * @ProjectName:
 * @Date: 2018/9/27 17:17
 * @Author: TFive
 */
@Configuration
public class ShiroConfiguration {


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager manager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(manager);

        bean.setLoginUrl("/admin/login");

        bean.setSuccessUrl("/admin/index");

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/*","anon");
        filterChainDefinitionMap.put("/admin/login","anon");
        filterChainDefinitionMap.put("/admin/*","authc");
        filterChainDefinitionMap.put("/admin/**","authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }




}

package com.github.lh.authentication;

import com.github.lh.dao.UserRepo;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@Configuration
public class ShiroConfig {

    @Autowired
    UserRepo userRepo;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean sfb = new ShiroFilterFactoryBean();

        sfb.setSecurityManager(securityManager);

        sfb.setLoginUrl("/login");
        sfb.setSuccessUrl("/login");
        sfb.setUnauthorizedUrl("/login");

        Map<String, Filter> filters = new HashMap<>(2);
        filters.put("authc", new FormAuthenticationFilter());
        filters.put("jwt", new JwtFilter());
        sfb.setFilters(filters);

        Map<String, String> filterChains = new HashMap<>(10);
        filterChains.put("/static/**", "anon");
        // 需要登录后跳转， 这里需要设置成authc
        filterChains.put("/login", "anon");
        filterChains.put("/users**", "jwt");

        sfb.setFilterChainDefinitionMap(filterChains);

        return sfb;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(new StatelessSubjectFactory());
        securityManager.setRealms(Arrays.asList(userRealm(), jwtRealm()));

        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Arrays.asList(userRealm(), jwtRealm()));
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());

        securityManager.setAuthenticator(authenticator);
        return securityManager;
    }

    @Bean
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm(userRepo);
    }
}

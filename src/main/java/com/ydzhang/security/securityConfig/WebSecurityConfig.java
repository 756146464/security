package com.ydzhang.security.securityConfig;

import com.ydzhang.security.securityConfig.handle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    //匿名用户访问无权限资源时的异常
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;
    CustomizeAccessDecisionManager customizeAccessDecisionManager;
    CustomizeFilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource;
    @Autowired
    private void setHandle(CustomizeAuthenticationEntryPoint authenticationEntryPoint,CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler,
                           CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler,CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler,
                           CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy,CustomizeAccessDecisionManager customizeAccessDecisionManager,
                           CustomizeFilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource){
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customizeAuthenticationFailureHandler = customizeAuthenticationFailureHandler;
        this.customizeAuthenticationSuccessHandler = customizeAuthenticationSuccessHandler;
        this.customizeLogoutSuccessHandler = customizeLogoutSuccessHandler;
        this.customizeSessionInformationExpiredStrategy  = customizeSessionInformationExpiredStrategy;
        this.customizeAccessDecisionManager = customizeAccessDecisionManager;
        this.customizeFilterInvocationSecurityMetadataSource = customizeFilterInvocationSecurityMetadataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.cors().and().csrf().disable();
        //http相关的配置，包括登入登出、异常处理、会话管理等
        http.authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customizeAccessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(customizeFilterInvocationSecurityMetadataSource);//安全元数据源
                        return o;
                    }
                }).
        //登入
        and().formLogin().
                permitAll().//允许所有用户
                successHandler(customizeAuthenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(customizeAuthenticationFailureHandler).//登录失败处理逻辑
        //登出
        and().logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(customizeLogoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID").//用户登出后删除cookie
        //异常处理提示
        and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).
        //会话管理
        and().sessionManagement().
                maximumSessions(1).//同一账号同时登录最大用户数
                expiredSessionStrategy(customizeSessionInformationExpiredStrategy);//会话信息过期策略会话信息过期策略(账号被挤下线)
    }
}

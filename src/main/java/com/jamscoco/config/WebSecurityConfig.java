package com.jamscoco.config;

import com.jamscoco.config.security.JwtAuthenticationFilter;
import com.jamscoco.config.security.MyAbstractSecurityInterceptor;
import com.jamscoco.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;//未登录无权限时处理逻辑

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;//jwt认证过滤器

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;//登录后权限拒绝处理逻辑

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;//登录失败处理逻辑

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;//登录成功处理逻辑

    @Autowired
    private AccessDecisionManager accessDecisionManager;//访问决策管理器

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;//安全元数据源

    @Autowired
    private MyAbstractSecurityInterceptor securityInterceptor;//授权过滤器

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;//登出成功处理逻辑

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService UserDetailsServiceImpl;//获取用户及权限信息Service

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域以及关闭CSRF
        http.cors().and().csrf().disable();
        //设置
        http.authorizeRequests().
                withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//访问决策管理器（判断当前用户是否满足权限）
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源（查询目标请求所需权限）
                        return o;
                    }
                });
        http.exceptionHandling().
                accessDeniedHandler(accessDeniedHandler).//登录后权限拒绝处理逻辑
                authenticationEntryPoint(authenticationEntryPoint);//未登录无权限时处理逻辑
        http.formLogin().
                permitAll().//允许所有用户
                        successHandler(authenticationSuccessHandler).//登录成功处理逻辑
                        failureHandler(authenticationFailureHandler);//登录失败处理逻辑
        http.logout().
                permitAll().//允许所有用户
                logoutSuccessHandler(logoutSuccessHandler);//登出成功处理逻辑
        //不创建session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //将jwt认证过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //将授权过滤器添加到默认拦截链中
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置获取用户及权限信息方式
        auth.userDetailsService(UserDetailsServiceImpl);
    }
}

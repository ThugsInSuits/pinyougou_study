package com.pinyougou.manager.controller;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @Classname SpringSecurityConfig
 * @Description TODO
 * @Date 2020/6/23 8:08
 * @Created by Administrator
 */
@Component
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /***
     * 公共资源取消安全校验
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/plugins/**");
        web.ignoring().antMatchers("/login.html");
        web.ignoring().antMatchers("/login_error.html");
    }

    /****
     * 安全校验配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //其他任何路径都需要管理员登录
        http.authorizeRequests().antMatchers("/**").access("hasRole('ADMIN')");

        //登录相关配置
        http.formLogin().loginPage("/login.html")   //指定登录地址
                .loginProcessingUrl("/login")       //指定处理登录的请求地址
                .defaultSuccessUrl("/admin/index.html",true); //登录成功后总是跳转到/admin/index.html页面

        //登出配置
        http.logout().logoutUrl("/logout").invalidateHttpSession(true); //登出地址为/logout，并且登出后销毁session

        //设置用户只允许在一处登录，在其他地方登录则挤掉已登录用户，被挤掉的已登录用户则需要返回/login.html重新登录
        http.sessionManagement().maximumSessions(1).expiredUrl("/login.html");

        //关闭CSRF安全策略
        http.csrf().disable();

        //允许跳转显示iframe
        http.headers().frameOptions().disable();

        //异常处理页面，例如没有权限访问等
        http.exceptionHandling().accessDeniedPage("/login_error.html");
    }


    /****
     * 用户授权信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里给一个固定账号模拟，后面商家登录我们就从数据库查询
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
    }
}

package com.zenquizz.web.security.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE = "/static/html/login.html";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SpringSocialConfigurer springSocialConfigurer = new SpringSocialConfigurer()
            .alwaysUsePostLoginUrl(true)
            .postLoginUrl("/securedHello")
            .defaultFailureUrl(LOGIN_PAGE + "?loginFailed=true");

        http
            .authorizeRequests()
                .antMatchers("/", "/test", "/hello", "/signin/google").permitAll()
                .antMatchers("/securedHello").hasRole("USER")
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin().disable()
            .anonymous().and()
            .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(LOGIN_PAGE + "?param.action=loggedOut")
                .and()
            .rememberMe()
                .and()
                .csrf().disable()
            .apply(springSocialConfigurer);
    }
}
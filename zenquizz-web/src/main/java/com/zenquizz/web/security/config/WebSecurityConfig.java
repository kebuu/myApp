package com.zenquizz.web.security.config;

import com.zenquizz.web.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE = "/static/html/login.html";

    @Autowired RememberMeServices rememberMeServices;
    @Autowired AppConfig appConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SpringSocialConfigurer springSocialConfigurer = new SpringSocialConfigurer();

        http
            .exceptionHandling()
                .accessDeniedPage(LOGIN_PAGE + "?loginNeeded=true")
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(LOGIN_PAGE))
                .and()
            .authorizeRequests()
                .antMatchers("/", "/test", "/hello", "/signin/google").permitAll()
                .antMatchers("/securedHello", "/angular", "/react", "/test/list").hasRole("USER")
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin().disable()
            .anonymous().and()
            .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl(LOGIN_PAGE + "?param.action=loggedOut")
                .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices)
                .key(appConfig.getRememberMeKey())
                .and()
                .csrf().disable()
            .apply(springSocialConfigurer);
    }
}
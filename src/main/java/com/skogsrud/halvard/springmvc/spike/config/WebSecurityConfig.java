package com.skogsrud.halvard.springmvc.spike.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/hello*").permitAll()  // no authentication on endpoints '/' and '/hello*'
                .anyRequest().authenticated()  // all other endpoints require authentication
                .and()
            .formLogin()
//                .loginPage("/login") // custom login page
                .permitAll()  // no authentication on logout endpoint
                .and()
            .logout()
                .permitAll();  // no authentication on logout endpoint
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
    }
}

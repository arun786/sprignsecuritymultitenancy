package com.arun.springsecuritymultitenancy.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author arun on 9/27/20
 */


@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> {
            auth.antMatchers("/h2-console/**").permitAll();
        })
                .authorizeRequests().anyRequest().authenticated().and().httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
}

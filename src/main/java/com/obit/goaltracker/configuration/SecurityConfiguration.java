package com.obit.goaltracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
//        http.antMatcher("h2-console").anonymous().and()
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .antMatcher("h2-console").anonymous()
                .and()
                .oauth2ResourceServer().jwt();
    }
}


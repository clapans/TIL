package com.project.blog.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeRequests().
                antMatchers("/member/join").hasAnyRole("USER").
                anyRequest().permitAll().
                and().
                formLogin().loginPage("/member/login").permitAll().
                and().build();
    }
}

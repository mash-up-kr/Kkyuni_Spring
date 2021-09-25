package com.example.ggyunispring.config.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().mvcMatchers("/**").permitAll()
        http.httpBasic().disable()
        http.cors().disable()
        http.csrf().disable()
        http.formLogin().disable()
    }

}
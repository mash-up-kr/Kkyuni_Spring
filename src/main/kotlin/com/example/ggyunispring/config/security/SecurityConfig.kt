package com.example.ggyunispring.config.security

import com.example.ggyunispring.common.jwt.JwtAuthenticationFilter
import com.example.ggyunispring.common.jwt.JwtProvider
import com.example.ggyunispring.domain.exception.ExceptionResponse
import com.example.ggyunispring.domain.exception.ExceptionType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/h2-console/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/v1/login/google",
            "/api/v1/login/refresh",
            "/error"
        )
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll() // CORS Preflight
            .antMatchers("/**").authenticated()

        http.httpBasic().disable()
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement().disable()

        http.addFilterBefore(JwtAuthenticationFilter(objectMapper, jwtProvider, userDetailsService), UsernamePasswordAuthenticationFilter::class.java)

        http.exceptionHandling()
            .authenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException? ->
                response.status = HttpStatus.UNAUTHORIZED.value()
                response.contentType = MediaType.APPLICATION_JSON_VALUE
                objectMapper.writeValue(
                    response.outputStream,
                    ExceptionResponse.of(ExceptionType.UNAUTHORIZED)
                )
            }
            .accessDeniedHandler { request: HttpServletRequest?, response: HttpServletResponse, accessDeniedException: AccessDeniedException? ->
                response.status = HttpStatus.FORBIDDEN.value()
                response.contentType = MediaType.APPLICATION_JSON_VALUE
                objectMapper.writeValue(
                    response.outputStream,
                    ExceptionResponse.of(ExceptionType.FORBIDDEN)
                )
            }
    }
}
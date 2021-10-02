package com.example.ggyunispring.common.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService
): GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token: String = jwtProvider.getTokenFromHeader(request as HttpServletRequest)
        if (jwtProvider.validateTokenIssuedDate(token)) {
            val authentication: Authentication = jwtProvider.getAuthentication(token, userDetailsService)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            val httpServletResponse = response as HttpServletResponse
            httpServletResponse.status = 400
            httpServletResponse.contentType = "application/json"
            httpServletResponse.characterEncoding = "utf8"
            httpServletResponse.writer.write("비정상 메시지")
            return
        }

        chain!!.doFilter(request, response)
    }
}
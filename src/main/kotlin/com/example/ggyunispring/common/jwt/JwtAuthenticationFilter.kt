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

        try {
            if (!jwtProvider.validateTokenIssuedDate(token)) {
                abnormalMessage(response)
                return
            }
            val authentication: Authentication = jwtProvider.getAuthentication(token, userDetailsService)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: Exception) {
            abnormalMessage(response)
            return
        }

        chain!!.doFilter(request, response)
    }

    private fun abnormalMessage(response: ServletResponse) {
        val httpServletResponse = response as HttpServletResponse
        httpServletResponse.status = 401
        httpServletResponse.contentType = "application/json"
        httpServletResponse.characterEncoding = "utf8"
        httpServletResponse.writer.write("비정상 메시지")
    }
}
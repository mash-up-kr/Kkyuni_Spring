package com.example.ggyunispring.common.jwt

import com.example.ggyunispring.domain.exception.ExceptionResponse
import com.example.ggyunispring.domain.exception.ExceptionType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
    private val userDetailsService: UserDetailsService
): GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val accessToken: String = resolveToken(request as HttpServletRequest)

        try {
            if (!jwtProvider.validateTokenIssuedDate(accessToken)) {
                val authentication: Authentication = jwtProvider.getAuthentication(accessToken, userDetailsService)
                SecurityContextHolder.getContext().authentication = authentication
            }

            chain!!.doFilter(request, response)
        } catch (e: Exception) {
            abnormalMessage(response as HttpServletResponse)
        }
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val accessToken = request.getHeader(AUTHORIZATION) ?: return "EMPTY"
        return accessToken.replace("Bearer", "").trim()
    }

    private fun abnormalMessage(response: HttpServletResponse) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(
            response.outputStream,
            ExceptionResponse.of(ExceptionType.UNAUTHORIZED)
        )
    }
}
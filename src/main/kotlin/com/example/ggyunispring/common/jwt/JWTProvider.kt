package com.example.ggyunispring.common.jwt

import com.example.ggyunispring.common.enum.JWT.KEY_VALUE_EMPTY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    private val tokenValidTime: Long = 30 * 24 * 60 * 60 * 1000L
    private val refreshTokenValidTime: Long = 365 * 24 * 60 * 60 * 1000L

    companion object {
        val HEADER_NAME: String = "Authorization"
    }

    @PostConstruct
    fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.encodeToByteArray())
    }

    fun createToken(sub: String): String {
        val claims = Jwts.claims().setSubject(sub)
        val date = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(date)
            .setExpiration(Date(date.time + tokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun createRefreshToken(sub: String): String {
        val claims = Jwts.claims().setSubject(sub)
        val date = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(date)
            .setExpiration(Date(date.time + refreshTokenValidTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthentication(token: String, userDetailsService: UserDetailsService): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getSubFromToken(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getSubFromToken(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun getClaims(token: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
    }

    fun getTokenFromHeader(request: HttpServletRequest): String {
        val accessToken = request.getHeader(HEADER_NAME) ?: return KEY_VALUE_EMPTY.name
        return accessToken.replace("Bearer", "").trim()
    }

    fun validateTokenIssuedDate(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            Date().before(claims.body.expiration)
        } catch (e: Exception) {
            false
        }
    }

}
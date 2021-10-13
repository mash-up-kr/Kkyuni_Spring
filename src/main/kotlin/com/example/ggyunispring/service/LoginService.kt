package com.example.ggyunispring.service

import com.example.ggyunispring.common.jwt.JwtProvider
import com.example.ggyunispring.domain.entity.Member
import com.example.ggyunispring.domain.repository.MemberRepository
import com.example.ggyunispring.dto.request.GoogleLoginRequestDTO
import com.example.ggyunispring.dto.response.LoginResponseDTO
import com.example.ggyunispring.error.member.GoogleIdTokenException
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class LoginService(
    private val memberRepository: MemberRepository,
    private val jwtProvider: JwtProvider,
    private val modelMapper: ModelMapper,
) {

    @Value("\${ggyuni.google.id}")
    private lateinit var clientId: String

    @Transactional
    fun googleLogin(googleLoginRequestDTO: GoogleLoginRequestDTO): LoginResponseDTO {
        // Google IdToken 검증 및 sub 값 추출
        val sub = verifyTokenAndGetSub(googleLoginRequestDTO.idToken)
        // sub 값 기반 유저 정보 조회(없다면 가입)
        val findMember = findMemberInfo(sub)
        // JWT Token 생성
        val token = jwtProvider.createToken(findMember.sub)
        // JWT Refresh Token 생성
        val refreshToken = jwtProvider.createRefreshToken(findMember.sub)
        findMember.updateToken(token)
        findMember.updateRefreshToken(refreshToken)
        return modelMapper.map(findMember, LoginResponseDTO::class.java)
    }
    // Google IdToken 검증 후 sub 값 가져오기
    private fun verifyTokenAndGetSub(idToken: String): String {
        val googleIdTokenVerifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), JacksonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList(clientId))
            .build()
        val token = googleIdTokenVerifier.verify(idToken) ?: throw GoogleIdTokenException()
        return token.payload.subject
    }

    private fun findMemberInfo(sub: String): Member {
        val findMember = memberRepository.findBySub(sub)
        return findMember.orElse(memberRepository.save(Member(sub = sub)))
    }

    fun login(): LoginResponseDTO {
        val userDetails = SecurityContextHolder.getContext().authentication.details as UserDetails
        val sub = userDetails.username
        val findMember = memberRepository.findBySub(sub).orElseThrow { throw Exception() }
        return modelMapper.map(findMember, LoginResponseDTO::class.java)
    }

}
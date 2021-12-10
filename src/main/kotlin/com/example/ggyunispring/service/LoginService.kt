package com.example.ggyunispring.service

import com.example.ggyunispring.common.jwt.JwtProvider
import com.example.ggyunispring.domain.entity.Member
import com.example.ggyunispring.domain.repository.MemberRepository
import com.example.ggyunispring.dto.request.GoogleLoginRequestDTO
import com.example.ggyunispring.dto.response.LoginResponseDTO
import com.example.ggyunispring.error.member.GoogleIdTokenException
import com.example.ggyunispring.error.member.InvalidJwtTokenException
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class LoginService(
    private val memberRepository: MemberRepository,
    private val jwtProvider: JwtProvider,
    private val modelMapper: ModelMapper,
): UserDetailsService {

    @Value("\${ggyuni.google.id}")
    private lateinit var clientId: String

//    @PostConstruct
//    fun init() {
//        val token = jwtProvider.createToken("1")
//        val refreshToken = jwtProvider.createRefreshToken("1")
//        memberRepository.save(Member(
//            sub = "testSub",
//            token = token,
//            refreshToken = refreshToken)
//        )
//    }

    @Transactional
    fun googleLogin(googleLoginRequestDTO: GoogleLoginRequestDTO): LoginResponseDTO {
        // Google IdToken 검증 및 sub 값 추출
        val sub = verifyTokenAndGetSub(googleLoginRequestDTO.idToken)
        // sub 값 기반 유저 정보 조회(없다면 가입)
        val findMember = findMemberInfo(sub)
        // JWT Token 생성
        val token = jwtProvider.createToken(findMember.memberID.toString())
        // JWT Refresh Token 생성
        val refreshToken = jwtProvider.createRefreshToken(findMember.memberID.toString())
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
        return findMember ?: memberRepository.save(Member(sub = sub))
    }

    fun login(): LoginResponseDTO {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val memberID = userDetails.username
        val findMember = memberRepository.findById(memberID.toLong()).orElseThrow { throw UsernameNotFoundException("Not Exist User") }
        return modelMapper.map(findMember, LoginResponseDTO::class.java)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("Not Exist User")
        }

        val findMember = memberRepository.findById(username.toLong()).orElseThrow{ throw UsernameNotFoundException("Not Exist User") }
        return User.builder().username(findMember.memberID.toString()).password("").roles("").build()
    }

    @Transactional
    fun updateToken(refreshToken: String): LoginResponseDTO {
        // jwt 검증(Secret Key, Date ... )
        if(!jwtProvider.validateTokenIssuedDate(refreshToken)) {
            throw InvalidJwtTokenException()
        }
        // 재발급
        val memberID = jwtProvider.getSubFromToken(refreshToken).toLong()
        val findMember = memberRepository.findByMemberIDAndRefreshToken(memberID, refreshToken) ?: throw InvalidJwtTokenException()
        val newToken = jwtProvider.createToken(memberID.toString())
        val newRefreshToken = jwtProvider.createRefreshToken(memberID.toString())
        // 저장
        findMember.updateToken(newToken)
        findMember.updateRefreshToken(newRefreshToken)
        return modelMapper.map(findMember, LoginResponseDTO::class.java)
    }

}
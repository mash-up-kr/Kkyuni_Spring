package com.example.ggyunispring.domain.member

import com.example.ggyunispring.domain.member.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface MemberRepository: CrudRepository<Member, Long> {
    fun findByToken(token: String): Member?
    fun findBySub(sub: String): Member?
    fun findByMemberIDAndRefreshToken(@Param("memberID") memberID: Long, @Param("refreshToken") refreshToken: String): Member?
}
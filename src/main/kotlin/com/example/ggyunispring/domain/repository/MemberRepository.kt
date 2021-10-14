package com.example.ggyunispring.domain.repository

import com.example.ggyunispring.domain.entity.Member
import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long> {
    fun findByToken(token: String): Member?
    fun findBySub(sub: String): Member?
}
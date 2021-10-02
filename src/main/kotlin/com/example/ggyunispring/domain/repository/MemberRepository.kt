package com.example.ggyunispring.domain.repository

import com.example.ggyunispring.domain.entity.Member
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MemberRepository: CrudRepository<Member, Long> {
    fun findByToken(token: String): Optional<Member>
    fun findBySub(sub: String): Optional<Member>
}
package com.example.ggyunispring.domain.member

import com.example.ggyunispring.domain.CreateModifyTime
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
class Member(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberID: Long? = null,
    val sub: String,
    var token: String? = null,
    var refreshToken: String? = null,

    @Embedded
    val createModifyTime: CreateModifyTime = CreateModifyTime()
) {

    fun updateToken(token: String) {
        this.token = token
    }

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }

}
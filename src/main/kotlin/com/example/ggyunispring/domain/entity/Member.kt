package com.example.ggyunispring.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val memberID: Long? = null,
    val sub: String,
    var token: String? = null,
    var refreshToken: String? = null
) {

    fun updateToken(token: String) {
        this.token = token
    }

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }

}
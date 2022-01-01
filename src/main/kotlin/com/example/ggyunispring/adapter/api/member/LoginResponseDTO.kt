package com.example.ggyunispring.adapter.api.member

data class LoginResponseDTO(
    val memberID: Long = 0L,
    val sub: String = "",
    var token: String = "",
    var refreshToken: String = ""
)
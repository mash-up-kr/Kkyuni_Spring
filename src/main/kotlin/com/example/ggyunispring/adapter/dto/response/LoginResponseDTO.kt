package com.example.ggyunispring.adapter.dto.response

data class LoginResponseDTO(
    val memberID: Long = 0L,
    val sub: String = "",
    var token: String = "",
    var refreshToken: String = ""
)
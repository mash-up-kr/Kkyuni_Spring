package com.example.ggyunispring.dto.response

data class LoginResponseDTO(
    val memberID: Long = 0L,
    val sub: String = "",
    var token: String = "",
    var refreshToken: String = ""
) {
}
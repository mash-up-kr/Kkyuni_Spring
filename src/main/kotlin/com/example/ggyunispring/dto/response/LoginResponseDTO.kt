package com.example.ggyunispring.dto.response

data class LoginResponseDTO(
    val memberID: Long,
    val sub: String,
    var token: String,
    var refreshToken: String
) {
}
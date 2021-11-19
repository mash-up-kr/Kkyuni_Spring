package com.example.ggyunispring.dto.request

import javax.validation.constraints.NotBlank

data class GoogleLoginRequestDTO(
    @field:NotBlank
    val idToken: String
)
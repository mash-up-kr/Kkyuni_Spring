package com.example.ggyunispring.adapter.api.member

import javax.validation.constraints.NotBlank

data class GoogleLoginRequestDTO(
    @field:NotBlank
    val idToken: String
)
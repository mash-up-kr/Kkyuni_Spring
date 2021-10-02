package com.example.ggyunispring.web

import com.example.ggyunispring.dto.request.GoogleLoginRequestDTO
import com.example.ggyunispring.dto.response.LoginResponseDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import com.example.ggyunispring.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/v1/login")
@RestController
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/google")
    fun googleLogin(@Valid @RequestBody googleLoginRequestDTO: GoogleLoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(200, loginService.googleLogin(googleLoginRequestDTO))
    }

    @PostMapping
    fun login(): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(200, loginService.login())
    }
}
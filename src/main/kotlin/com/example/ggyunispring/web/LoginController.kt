package com.example.ggyunispring.web

import com.example.ggyunispring.dto.request.GoogleLoginRequestDTO
import com.example.ggyunispring.dto.response.LoginResponseDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import com.example.ggyunispring.service.LoginService
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/api/v1/login")
@RestController
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/google")
    fun googleLogin(@Valid @RequestBody googleLoginRequestDTO: GoogleLoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(OK, loginService.googleLogin(googleLoginRequestDTO))
    }

    @PostMapping
    fun login(): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(OK, loginService.login())
    }

    @GetMapping("/refresh")
    fun updateToken(@RequestHeader("token") refreshToken: String): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(OK, loginService.updateToken(refreshToken))
    }
}
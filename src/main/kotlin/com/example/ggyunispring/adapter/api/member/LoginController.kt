package com.example.ggyunispring.adapter.api.member

import com.example.ggyunispring.adapter.ResponseDTO
import com.example.ggyunispring.application.LoginService
import org.springframework.http.HttpStatus.OK
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
        return ResponseDTO.success(OK, loginService.googleLogin(googleLoginRequestDTO))
    }

    @PostMapping
    fun login(): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.success(OK, loginService.login())
    }

    @GetMapping("/refresh")
    fun updateToken(@RequestHeader("token") refreshToken: String): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.success(OK, loginService.updateToken(refreshToken))
    }
}
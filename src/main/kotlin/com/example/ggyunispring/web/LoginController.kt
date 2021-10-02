package com.example.ggyunispring.web

import com.example.ggyunispring.domain.repository.LogoRepository
import com.example.ggyunispring.dto.request.GoogleLoginRequestDTO
import com.example.ggyunispring.dto.response.LoginResponseDTO
import com.example.ggyunispring.dto.response.LogoResponseDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import com.example.ggyunispring.service.LoginService
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/api/v1/login")
@RestController
class LoginController(
    private val logoRepository: LogoRepository,
    private val loginService: LoginService,
    private val modelMapper: ModelMapper
) {

    @GetMapping
    fun main(): ResponseEntity<LogoResponseDTO> {
        return ResponseDTO.of(200, modelMapper.map(logoRepository.findAll().first(), LogoResponseDTO::class.java))
    }

    @PostMapping("/google")
    fun googleLogin(@Valid @RequestBody googleLoginRequestDTO: GoogleLoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return ResponseDTO.of(200, loginService.googleLogin(googleLoginRequestDTO))
    }
}
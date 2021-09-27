package com.example.ggyunispring.web

import com.example.ggyunispring.domain.repository.LogoRepository
import com.example.ggyunispring.dto.response.LogoResponseDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/login")
@RestController
class LoginController(
    private val logoRepository: LogoRepository,
    private val modelMapper: ModelMapper
) {

    @GetMapping
    fun main(): ResponseEntity<LogoResponseDTO> {
        return ResponseDTO.of(200, modelMapper.map(logoRepository.findAll().first(), LogoResponseDTO::class.java))
    }
}
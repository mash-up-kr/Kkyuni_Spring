package com.example.ggyunispring.dto.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDTO {
    companion object {
        fun <T> of(code: HttpStatus, t: T): ResponseEntity<T> {
            return ResponseEntity.status(code.value()).body(t)
        }

        fun <T> error(t: T): ResponseEntity<T> {
            return ResponseEntity.status(500).body(t)
        }
    }
}
package com.example.ggyunispring.dto.response

import org.springframework.http.ResponseEntity

class ResponseDTO {
    companion object {
        fun <T> of(code: Int, t: T): ResponseEntity<T> {
            return ResponseEntity.status(code).body(t)
        }

        fun <T> error(t: T): ResponseEntity<T> {
            return ResponseEntity.status(500).body(t)
        }
    }
}
package com.example.ggyunispring.adapter

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDTO {
    companion object {
        fun <T> success(code: HttpStatus, t: T): ResponseEntity<T> {
            return ResponseEntity.status(code.value()).body(t)
        }
    }
}
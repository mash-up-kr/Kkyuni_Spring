package com.example.ggyunispring.adapter

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDTO {
    companion object {
        fun <T> of(code: HttpStatus, t: T): ResponseEntity<T> {
            return ResponseEntity.status(code.value()).body(t)
        }

        fun <T> failure(code: HttpStatus): ResponseEntity<T> {
            return ResponseEntity.status(code.value()).body(null);
        }
    }
}
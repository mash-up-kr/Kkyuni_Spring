package com.example.ggyunispring.adapter

import com.example.ggyunispring.domain.exception.ExceptionType

data class ExceptionResponse(
    val status: Int,
    val message: String
) {

    companion object {
        fun of(errorType: ExceptionType): ExceptionResponse {
            return ExceptionResponse(errorType.statusCode, errorType.responseMessage)
        }
    }
}
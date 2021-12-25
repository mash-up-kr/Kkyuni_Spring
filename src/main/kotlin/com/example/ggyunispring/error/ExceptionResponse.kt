package com.example.ggyunispring.error

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
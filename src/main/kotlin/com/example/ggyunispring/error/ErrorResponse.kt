package com.example.ggyunispring.error

data class ErrorResponse(
    val status: Int,
    val message: String
) {

    companion object {
        fun of(errorType: ErrorType): ErrorResponse {
            return ErrorResponse(errorType.statusCode, errorType.responseMessage)
        }
    }
}
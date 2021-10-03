package com.example.ggyunispring.error

/**
 * created by Gyunny 2021/10/03
 */
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
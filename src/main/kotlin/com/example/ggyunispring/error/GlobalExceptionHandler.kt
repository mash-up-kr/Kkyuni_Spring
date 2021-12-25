package com.example.ggyunispring.error

import com.example.ggyunispring.error.member.GoogleIdTokenException
import com.example.ggyunispring.error.member.InvalidJwtTokenException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * 구글 ID 토큰이 잘못되었을 경우
     */
    @ExceptionHandler(GoogleIdTokenException::class)
    fun invalidGoogleIdTokenException(): ResponseEntity<ExceptionResponse> {
        val errorType = ExceptionType.INVALID_GOOGLE_ID_TOKEN
        return ResponseEntity(ExceptionResponse.of(errorType), HttpStatus.BAD_REQUEST)
    }

    /**
     * DateTimeFormat 이 맞지 않게 클라이언트로 넘어올 때 예외 처리
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMisMatchException(): ResponseEntity<ExceptionResponse> {
        val errorType = ExceptionType.INVALID_DATE_INPUT_VALUE
        return ResponseEntity(ExceptionResponse.of(errorType), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidJwtTokenException::class)
    fun invalidJwtTokenException(): ResponseEntity<ExceptionResponse> {
        val errorType = ExceptionType.UNAUTHORIZED
        return ResponseEntity(ExceptionResponse.of(errorType), HttpStatus.BAD_REQUEST)
    }

}
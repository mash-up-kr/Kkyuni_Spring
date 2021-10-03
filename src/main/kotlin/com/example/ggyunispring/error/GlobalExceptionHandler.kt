package com.example.ggyunispring.error

import com.example.ggyunispring.error.member.GoogleIdTokenException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

/**
 * created by Gyunny 2021/10/03
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * Entity 가 존재하지 않을 때 204 반환
     */
    @ExceptionHandler(EntityNotFoundException::class)
    fun diaryNotFoundException(): ResponseEntity<Any> {
        return ResponseEntity.status(204).body(null)
    }

    /**
     * 구글 ID 토큰이 잘못되었을 경우
     */
    @ExceptionHandler(GoogleIdTokenException::class)
    fun invalidGoogleIdTokenException(): ResponseEntity<ErrorResponse> {
        val errorType = ErrorType.INVALID_GOOGLE_ID_TOKEN
        return ResponseEntity(ErrorResponse.of(errorType), HttpStatus.BAD_REQUEST)
    }

    /**
     * DateTimeFormat 이 맞지 않게 클라이언트로 넘어올 때 예외 처리
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMisMatchException(): ResponseEntity<ErrorResponse> {
        val errorType = ErrorType.INVALID_DATE_INPUT_VALUE
        return ResponseEntity(ErrorResponse.of(errorType), HttpStatus.BAD_REQUEST)
    }

}
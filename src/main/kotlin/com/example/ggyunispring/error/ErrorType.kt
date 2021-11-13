package com.example.ggyunispring.error

/**
 * created by Gyunny 2021/10/03
 */
enum class ErrorType(
    val statusCode: Int,
    val responseMessage: String
) {

    INVALID_DATE_INPUT_VALUE(400, "날짜 형식이 올바르지 않습니다."),
    INVALID_GOOGLE_ID_TOKEN(400, "구글 ID Token 이 올바르지 않습니다"),
    METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),
    INVALID_TYPE_VALUE(400, "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),
    INVALID_JWT_TOKEN(400, "토큰이 올바르지 않습니다."),
    ;

}

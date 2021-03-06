package com.example.ggyunispring.domain.exception

enum class ExceptionType(
    val statusCode: Int,
    val responseMessage: String
) {

    INVALID_DATE_INPUT_VALUE(400, "날짜 형식이 올바르지 않습니다."),
    INVALID_GOOGLE_ID_TOKEN(400, "구글 ID Token 이 올바르지 않습니다"),
    METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),
    INVALID_TYPE_VALUE(400, "Invalid Type Value"),
    FORBIDDEN(403, "Access is Denied"),
    UNAUTHORIZED(401, "Authentication Exception"),
    ;

}

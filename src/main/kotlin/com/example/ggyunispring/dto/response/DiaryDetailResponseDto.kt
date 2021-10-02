package com.example.ggyunispring.dto.response

/**
 * created by Gyunny 2021/10/02
 */
data class DiaryDetailResponseDto(
    val diaryId: Long = 0L,
    val musicThumbnailImage: String = "",
    val title: String = "",
    val content: String = ""
)
package com.example.ggyunispring.dto.response

/**
 * created by Gyunny 2021/10/02
 */
data class DiaryDetailResponseDTO(
    val diaryID: Long = 0L,
    val musicLink: String = "",
    val musicThumbnailImage: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = ""
)
package com.example.ggyunispring.dto.response

import java.time.LocalDate

/**
 * created by Gyunny 2021/10/02
 */
data class DiaryResponseDTO(
    val diaryID: Long = 0L,
    val youtubeLink: String = "",
    val musicThumbnailImage: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val writingDate: LocalDate = LocalDate.MIN
)
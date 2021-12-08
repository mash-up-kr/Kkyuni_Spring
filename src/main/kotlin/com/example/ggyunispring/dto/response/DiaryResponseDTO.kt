package com.example.ggyunispring.dto.response

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import java.time.LocalDate

data class DiaryResponseDTO(
    val diaryID: Long = 0L,
    val youtubeLink: String = "",
    val webViewURL: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val writingDate: LocalDate = LocalDate.MIN,
    val diaryType: DiaryType = DiaryType.YELLOW1,
    val emotion: Emotion = Emotion.sad
)
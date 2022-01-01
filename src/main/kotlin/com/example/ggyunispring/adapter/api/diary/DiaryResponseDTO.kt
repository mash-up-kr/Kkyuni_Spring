package com.example.ggyunispring.adapter.api.diary

import com.example.ggyunispring.domain.diary.DiaryType
import com.example.ggyunispring.domain.diary.Emotion
import java.time.LocalDate

data class DiaryResponseDTO(
    val diaryID: Long = 0L,
    val youtubeLink: String = "",
    val webViewURL: String = "",
    val musicThumbnailImageUrl: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val writingDate: LocalDate = LocalDate.MIN,
    val diaryType: DiaryType = DiaryType.YELLOW1,
    val emotion: Emotion = Emotion.SAD
)
package com.example.ggyunispring.dto.response

import com.example.ggyunispring.domain.enum.DiaryType
import com.example.ggyunispring.domain.enum.Emotion
import java.time.LocalDate

data class CreateDiaryResponseDTO(
    val diaryID: Long = 0L,
    val emotion: Emotion = Emotion.SAD,
    val diaryType: DiaryType = DiaryType.YELLOW1,
    val musicTitle: String = "",
    val youtubeLink: String = "",
    val webViewURL: String = "",
    val musicThumbnailImageUrl: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val writingDate: LocalDate = LocalDate.MIN
)
package com.example.ggyunispring.dto.response

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import java.time.LocalDate

/**
 * created by Gyunny 2021/10/02
 */
data class CreateDiaryResponseDTO(
    val diaryID: Long = 0L,
    val emotion: Emotion = Emotion.sad,
    val diaryType: DiaryType = DiaryType.YELLOW1,
    val musicTitle: String = "",
    val youtubeLink: String = "",
    val musicThumbnailImage: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val writingDate: LocalDate = LocalDate.MIN
)
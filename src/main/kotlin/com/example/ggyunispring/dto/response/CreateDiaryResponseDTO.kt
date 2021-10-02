package com.example.ggyunispring.dto.response

import com.example.ggyunispring.common.enum.Emotion

/**
 * created by Gyunny 2021/10/02
 */
data class CreateDiaryResponseDTO(
    val diaryID: Long = 0L,
    val emotion: Emotion = Emotion.ANGRY,
    val musicTitle: String = "",
    val musicLink: String = "",
    val musicThumbnailImage: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = ""
)
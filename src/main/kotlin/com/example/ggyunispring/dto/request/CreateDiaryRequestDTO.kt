package com.example.ggyunispring.dto.request

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class CreateDiaryRequestDTO(

    @NotNull
    val emotion: Emotion,

    @NotNull
    val diaryType: DiaryType,

    @field:Length(min = 1, max = 200)
    val musicLink: String,

    @field:Length(min = 1, max = 30)
    val musicTitle: String,

    @field:Length(min = 1, max = 200)
    val musicThumbnailImage: String,

    val musicPlayTime: Double,

    @field:Length(min = 1, max = 20)
    val title: String,

    @field:Length(min = 1, max = 100)
    val content: String

)
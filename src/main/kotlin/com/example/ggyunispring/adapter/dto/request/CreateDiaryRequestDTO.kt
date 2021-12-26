package com.example.ggyunispring.adapter.dto.request

import com.example.ggyunispring.domain.diary.DiaryType
import com.example.ggyunispring.domain.diary.Emotion
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class CreateDiaryRequestDTO(

    @NotNull
    val emotion: Emotion,

    @NotNull
    val diaryType: DiaryType,

    @field:Length(min = 1, max = 200)
    val youtubeLink: String,

    @field:Length(min = 1, max = 200)
    val musicTitle: String,

    @field:Length(min = 1)
    val webViewURL: String,

    @field:Length(min = 1)
    val musicThumbnailImageUrl: String,

    val musicPlayTime: Double,

    @field:Length(min = 1, max = 20)
    val title: String,

    @field:Length(min = 1, max = 100)
    val content: String,

    @NotNull
    val writingDate: LocalDate,

    @field:Length(min = 1)
    val latitude: String,

    @field:Length(min = 1)
    val longitude: String,

    )
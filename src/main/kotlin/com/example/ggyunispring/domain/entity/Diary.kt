package com.example.ggyunispring.domain.entity

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import java.time.LocalDate
import javax.persistence.*

@Entity
class Diary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val musicTitle: String = "",
    val youtubeLink: String = "",
    val webViewURL: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val writingDate: LocalDate = LocalDate.now(),
    private var memberId: Long = 0L,

    @Enumerated(value = EnumType.STRING)
    val diaryType: DiaryType = DiaryType.YELLOW1,

    @Enumerated(value = EnumType.STRING)
    val emotion:Emotion = Emotion.sad,

    @Embedded
    val createModifyTime: CreateModifyTime = CreateModifyTime()
) {

    fun updateDiaryMemberId(memberId: Long) {
        this.memberId = memberId;
    }
}
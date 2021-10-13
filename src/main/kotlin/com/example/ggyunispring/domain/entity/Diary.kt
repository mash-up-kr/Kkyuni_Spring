package com.example.ggyunispring.domain.entity

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
class Diary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val musicTitle: String = "",
    val youtubeLink: String = "",
    val musicThumbnailImage: String = "",
    val musicPlayTime: Double = 0.0,
    val title: String = "",
    val content: String = "",
    val writingDate: LocalDate = LocalDate.now(),

    @Enumerated(value = EnumType.STRING)
    val diaryType: DiaryType = DiaryType.YELLOW1,

    @Enumerated(value = EnumType.STRING)
    val emotion:Emotion = Emotion.sad,

    @Embedded
    val createModifyTime: CreateModifyTime = CreateModifyTime()
)
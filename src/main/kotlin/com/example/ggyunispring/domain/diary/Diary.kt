package com.example.ggyunispring.domain.diary

import com.example.ggyunispring.domain.CreateModifyTime
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
class Diary(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val musicTitle: String = "",
    val youtubeLink: String = "",
    val webViewURL: String = "",
    val musicThumbnailImageUrl: String = "",
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
    val emotion: Emotion = Emotion.SAD,

    @Embedded
    val createModifyTime: CreateModifyTime = CreateModifyTime()
) {

    fun updateDiaryMemberId(memberId: Long) {
        this.memberId = memberId;
    }
}
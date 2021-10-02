package com.example.ggyunispring.domain.entity

import com.example.ggyunispring.common.enum.Emotion
import javax.persistence.*

@Entity
class Diary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val musicTitle: String,
    val musicLink: String,
    val musicThumbnailImage: String,
    val musicPlayTime: Double,
    val title: String,
    val content: String,
    @Enumerated(value = EnumType.STRING)
    val emotion:Emotion
) {
}
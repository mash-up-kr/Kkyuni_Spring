package com.example.ggyunispring.domain.entity

import com.example.ggyunispring.common.enum.Emotion
import javax.persistence.*

@Entity
class Diary(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    val musicTitle: String,
    val musicThumbnail: String,
    val musicPlayTime: Double,
    val title: String,
    val content: String,
    @Enumerated(value = EnumType.STRING)
    val emotion:Emotion
) {
}
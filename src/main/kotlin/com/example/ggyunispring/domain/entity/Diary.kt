package com.example.ggyunispring.domain.entity

import com.example.ggyunispring.common.enum.Emotion
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Diary(
    @Id
    val id: String,
    val title: String,
    val content: String,
    @Enumerated(value = EnumType.STRING)
    val emotion:Emotion
) {
}
package com.example.ggyunispring.dto.request

import com.example.ggyunispring.common.enum.Emotion
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class CreateDiaryRequestDTO(

    @field:NotBlank
    val emotion: Emotion,

    @field:Length(min = 1, max = 20)
    val title: String,

    @field:Length(min = 1, max = 100)
    val content: String

)
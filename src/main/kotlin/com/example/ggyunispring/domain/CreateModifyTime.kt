package com.example.ggyunispring.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Embeddable

@Embeddable
data class CreateModifyTime(
    @CreatedDate
    var createTime: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate
    var lastModifiedTime: LocalDateTime = LocalDateTime.MIN
)
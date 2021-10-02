package com.example.ggyunispring.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Embeddable

/**
 * created by Gyunny 2021/10/02
 */
@Embeddable
data class CreateModifyTime(
    @CreatedDate
    var createTime: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate
    var lastModifiedTime: LocalDateTime = LocalDateTime.MIN
)
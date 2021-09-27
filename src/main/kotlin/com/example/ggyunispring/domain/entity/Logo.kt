package com.example.ggyunispring.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Logo(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,
    val url: String
) {
}
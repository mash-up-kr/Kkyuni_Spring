package com.example.ggyunispring.domain.repository

import com.example.ggyunispring.domain.entity.Diary
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

/**
 * created by Gyunny 2021/10/02
 */
interface DiaryRepository : CrudRepository<Diary, Long> {

    fun findAllByWritingDateBetween(startDayOfMonth: LocalDate, endDayOfMonth: LocalDate): List<Diary>
    fun findByWritingDate(localDate: LocalDate): Diary?
}
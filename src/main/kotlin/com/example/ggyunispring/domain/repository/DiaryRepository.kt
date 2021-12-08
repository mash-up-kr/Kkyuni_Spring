package com.example.ggyunispring.domain.repository

import com.example.ggyunispring.domain.entity.Diary
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface DiaryRepository : CrudRepository<Diary, Long> {

    fun findAllByWritingDateBetweenAndMemberId(startDayOfMonth: LocalDate, endDayOfMonth: LocalDate, memberId: Long): List<Diary>
    fun findByWritingDateAndMemberId(localDate: LocalDate, memberId: Long): Diary?
}
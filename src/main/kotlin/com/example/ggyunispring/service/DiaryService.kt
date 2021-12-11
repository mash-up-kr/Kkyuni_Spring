package com.example.ggyunispring.service

import com.example.ggyunispring.domain.entity.Diary
import com.example.ggyunispring.domain.repository.DiaryRepository
import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.CreateDiaryResponseDTO
import com.example.ggyunispring.dto.response.DiaryResponseDTO
import org.modelmapper.ModelMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.YearMonth

@Transactional(readOnly = true)
@Service
class DiaryService(
    private val diaryRepository: DiaryRepository,
    private val modelMapper: ModelMapper
) {

    @Transactional
    fun createDiary(createDiaryRequestDTO: CreateDiaryRequestDTO): CreateDiaryResponseDTO {
        val diary = modelMapper.map(createDiaryRequestDTO, Diary::class.java)
        diary.updateDiaryMemberId(extractMemberId())
        return modelMapper.map(diaryRepository.save(diary), CreateDiaryResponseDTO::class.java)
    }

    fun findByDiaryWritingDate(localDate: LocalDate): DiaryResponseDTO? {
        val diary = diaryRepository.findByWritingDateAndMemberId(localDate, extractMemberId()) ?: DiaryResponseDTO()
        return modelMapper.map(diary, DiaryResponseDTO::class.java);
    }

    fun findListByDate(yearMonth: YearMonth): List<DiaryResponseDTO> {
        val startDayOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        val endDayOfMonth = startDayOfMonth.withDayOfMonth(startDayOfMonth.lengthOfMonth())
        return diaryRepository.findAllByWritingDateBetweenAndMemberId(startDayOfMonth, endDayOfMonth, extractMemberId())
            .map { modelMapper.map(it, DiaryResponseDTO::class.java) }
    }

    private fun extractMemberId(): Long {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        return userDetails.username.toLong()
    }

}
package com.example.ggyunispring.service

import com.example.ggyunispring.domain.entity.Diary
import com.example.ggyunispring.domain.repository.DiaryRepository
import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.CreateDiaryResponseDTO
import com.example.ggyunispring.dto.response.DiaryResponseDTO
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth
import javax.transaction.Transactional

/**
 * created by Gyunny 2021/10/02
 */
@Service
class DiaryService(
    private val diaryRepository: DiaryRepository,
    private val modelMapper: ModelMapper
) {

    @Transactional
    fun createDiary(createDiaryRequestDTO: CreateDiaryRequestDTO): CreateDiaryResponseDTO {
        val diary = diaryRepository.save(modelMapper.map(createDiaryRequestDTO, Diary::class.java))
        return modelMapper.map(diary, CreateDiaryResponseDTO::class.java);
    }

    fun findByDiaryID(localDate: LocalDate): DiaryResponseDTO {
        return modelMapper.map(diaryRepository.findByWritingDate(localDate), DiaryResponseDTO::class.java);
    }

    fun findListByDate(yearMonth: YearMonth): List<DiaryResponseDTO> {
        val startDayOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        val endDayOfMonth = startDayOfMonth.withDayOfMonth(startDayOfMonth.lengthOfMonth())
        return diaryRepository.findAllByWritingDateBetween(startDayOfMonth, endDayOfMonth)
            .map { modelMapper.map(it, DiaryResponseDTO::class.java) }

    }

}
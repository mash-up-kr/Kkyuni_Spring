package com.example.ggyunispring.service

import com.example.ggyunispring.common.enum.DiaryType
import com.example.ggyunispring.common.enum.Emotion
import com.example.ggyunispring.domain.entity.Diary
import com.example.ggyunispring.domain.repository.DiaryRepository
import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.CreateDiaryResponseDTO
import com.example.ggyunispring.dto.response.DiaryResponseDTO
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.YearMonth
import javax.annotation.PostConstruct

/**
 * created by Gyunny 2021/10/02
 */
@Service
class DiaryService(
    private val diaryRepository: DiaryRepository,
    private val modelMapper: ModelMapper
) {

    @PostConstruct
    fun init() {
        repeat(10) {
            diaryRepository.save(Diary(
                musicTitle = "musicTitle${it}",
                youtubeLink = "youtubeLink${it}",
                musicThumbnailImage = "musicThumbnailImage${it}",
                musicPlayTime = 0.0,
                title = "title${it}",
                content = "content${it}",
                writingDate = LocalDate.of(2021, it + 1, 1),
                diaryType = DiaryType.YELLOW1,
                emotion = Emotion.happy
            ))
        }

    }

    @Transactional
    fun createDiary(createDiaryRequestDTO: CreateDiaryRequestDTO): CreateDiaryResponseDTO {
        val diary = diaryRepository.save(modelMapper.map(createDiaryRequestDTO, Diary::class.java))
        return modelMapper.map(diary, CreateDiaryResponseDTO::class.java)
    }

    @Transactional(readOnly = true)
    fun findByDiaryWritingDate(localDate: LocalDate): DiaryResponseDTO? {
        val diary = diaryRepository.findByWritingDate(localDate) ?: return null
        return modelMapper.map(diary, DiaryResponseDTO::class.java);
    }

    @Transactional(readOnly = true)
    fun findListByDate(yearMonth: YearMonth): List<DiaryResponseDTO> {
        val startDayOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        val endDayOfMonth = startDayOfMonth.withDayOfMonth(startDayOfMonth.lengthOfMonth())
        return diaryRepository.findAllByWritingDateBetween(startDayOfMonth, endDayOfMonth)
            .map { modelMapper.map(it, DiaryResponseDTO::class.java) }
    }

}
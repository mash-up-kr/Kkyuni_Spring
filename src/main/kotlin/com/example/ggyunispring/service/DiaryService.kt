package com.example.ggyunispring.service

import com.example.ggyunispring.domain.entity.Diary
import com.example.ggyunispring.domain.repository.DiaryRepository
import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.DiaryDetailResponseDto
import com.example.ggyunispring.error.DiaryNotFoundException
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
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
    fun createDiary(createDiaryRequestDTO: CreateDiaryRequestDTO) {
        diaryRepository.save(modelMapper.map(createDiaryRequestDTO, Diary::class.java))
    }

    fun findById(diaryId: Long): DiaryDetailResponseDto {
        val diary = diaryRepository.findById(diaryId).orElseThrow { throw DiaryNotFoundException() }
        return modelMapper.map(diary, DiaryDetailResponseDto::class.java);
    }

}
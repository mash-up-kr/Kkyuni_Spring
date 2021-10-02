package com.example.ggyunispring.service

import com.example.ggyunispring.domain.entity.Diary
import com.example.ggyunispring.domain.repository.DiaryRepository
import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.CreateDiaryResponseDTO
import com.example.ggyunispring.dto.response.DiaryDetailResponseDTO
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
    fun createDiary(createDiaryRequestDTO: CreateDiaryRequestDTO): CreateDiaryResponseDTO {
        val diary = diaryRepository.save(modelMapper.map(createDiaryRequestDTO, Diary::class.java))
        return modelMapper.map(diary, CreateDiaryResponseDTO::class.java);
    }

    fun findById(diaryId: Long): DiaryDetailResponseDTO {
        val diary = diaryRepository.findById(diaryId).orElseThrow { throw DiaryNotFoundException() }
        return modelMapper.map(diary, DiaryDetailResponseDTO::class.java);
    }

}
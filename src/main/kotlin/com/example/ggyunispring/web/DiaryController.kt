package com.example.ggyunispring.web

import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.CreateDiaryResponseDTO
import com.example.ggyunispring.dto.response.DiaryDetailResponseDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import com.example.ggyunispring.service.DiaryService
import io.swagger.annotations.ApiOperation
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.YearMonth
import javax.validation.Valid

@RequestMapping("/api/v1/diary")
@RestController
class DiaryController(
    private val diaryService: DiaryService
) {

    @ApiOperation("해당 월에 대한 다이어리 전체 조회")
    @GetMapping("/{date}/list")
    fun getDiaryList(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") yearMonth: YearMonth): ResponseEntity<Any> {
        diaryService.findListByDate(yearMonth);
        return ResponseDTO.of(200, "test")
    }

    @ApiOperation("다이어리 하나 상세 조회")
    @GetMapping("/{diaryId}")
    fun getDiaryDetails(@PathVariable diaryId: Long): ResponseEntity<DiaryDetailResponseDTO> {
        return ResponseDTO.of(200, diaryService.findByDiaryID(diaryId))
    }

    @ApiOperation("다이어리 생성")
    @PostMapping("")
    fun createDiary(@Valid @RequestBody createDiaryRequestDTO: CreateDiaryRequestDTO): ResponseEntity<CreateDiaryResponseDTO> {
        return ResponseDTO.of(200, diaryService.createDiary(createDiaryRequestDTO))
    }

}
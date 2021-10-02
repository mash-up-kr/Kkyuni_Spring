package com.example.ggyunispring.web

import com.example.ggyunispring.dto.request.CreateDiaryRequestDTO
import com.example.ggyunispring.dto.response.ResponseDTO
import com.example.ggyunispring.service.DiaryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.validation.Valid

@RequestMapping("/api/v1/diary")
@RestController
class DiaryController(
    private val diaryService: DiaryService
) {

    @GetMapping("/{date}/list")
    fun getDiaryList(@PathVariable("date") date: LocalDate): ResponseEntity<Any> {

        return ResponseDTO.of(200, "test")
    }

    @GetMapping("/{date}/details")
    fun getDiaryDetails(@PathVariable("date") date: LocalDate): ResponseEntity<Any> {
        return ResponseDTO.of(200, "test")
    }

    @PostMapping("")
    fun createDiary(@Valid @RequestBody createDiaryRequestDTO: CreateDiaryRequestDTO): ResponseEntity<Any> {
        diaryService.createDiary(createDiaryRequestDTO)
        return ResponseDTO.of(200, "test")
    }
}
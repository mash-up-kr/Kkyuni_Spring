package com.example.ggyunispring.web

import com.example.ggyunispring.service.S3Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/test")
@RestController
class TestController(
    private val s3Service: S3Service
) {

    @GetMapping
    fun test(): String {
        return "Gyunny CI CD Test~"
    }

    @PostMapping("/upload")
    fun uploadTest(@RequestPart("image") multipartFile: MultipartFile) {
        s3Service.upload(multipartFile)
    }

}
package com.example.ggyunispring.adapter.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/test")
@RestController
class TestController {

    @GetMapping
    fun test(): String {
        return "Gyunny Actions!@!@!@!@"
    }

}
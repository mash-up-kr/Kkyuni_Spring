package com.example.ggyunispring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class GgyuniSpringApplication

fun main(args: Array<String>) {
    runApplication<GgyuniSpringApplication>(*args)
}

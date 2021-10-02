package com.example.ggyunispring.service

import org.junit.jupiter.api.BeforeEach
import org.modelmapper.ModelMapper

/**
 * created by Gyunny 2021/10/03
 */
class DiaryServiceTest {

    lateinit var modelMapper: ModelMapper

    @BeforeEach
    fun init() {
        modelMapper = ModelMapper()
        modelMapper.configuration.isFieldMatchingEnabled = true
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
    }

}
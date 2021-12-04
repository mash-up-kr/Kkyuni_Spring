package com.example.ggyunispring.config.cors

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * created by Gyunny 2021/11/14
 */
@Configuration
@EnableWebMvc
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/v1/**")
//            .allowedOrigins("*")
            .allowedOriginPatterns("*")
            .allowedMethods("POST", "PUT", "DELETE")
            .allowedHeaders("Authorization")
            .allowCredentials(true).maxAge(3600)
    }
}
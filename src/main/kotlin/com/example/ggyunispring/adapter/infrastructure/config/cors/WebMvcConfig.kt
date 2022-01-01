package com.example.ggyunispring.adapter.infrastructure.config.cors

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/v1/**")
            .allowedOriginPatterns("*")
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Authorization")
            .allowCredentials(true).maxAge(3600)
    }
}
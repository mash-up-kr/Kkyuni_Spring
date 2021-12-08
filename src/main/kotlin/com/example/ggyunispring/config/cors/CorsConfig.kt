package com.example.ggyunispring.config.cors

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/v1/**")
            .allowedOriginPatterns("https://compassionate-wing-0abef6.netlify.app/", "http://localhost:5050")
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Authorization")
            .allowCredentials(true).maxAge(3600)
    }
}
package com.example.ggyunispring.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.ggyunispring"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(swaggerInfo())
            .useDefaultResponseMessages(false)
    }

    private fun swaggerInfo(): ApiInfo {
        return ApiInfoBuilder().title("GGYUNI Backend API Documentation")
            .description("베이글 뀨니뀨니의 API 문서입니다.")
            .version("1").build()
    }
}
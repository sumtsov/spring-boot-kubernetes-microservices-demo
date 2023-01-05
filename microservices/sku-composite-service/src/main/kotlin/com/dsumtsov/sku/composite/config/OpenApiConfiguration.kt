package com.dsumtsov.sku.composite.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun getOpenApiDocumentation(): OpenAPI = OpenAPI()
        .components(generateComponents())
        .addSecurityItem(generateSecurityItem())
        .info(
            Info()
                .title("sku-composite")
                .description("REST API for sku-composite microservice")
                .version("1.0.0")
                .contact(
                    Contact()
                        .name("Dmitry Sumtsov")
                        .email("contact@mail.com")
                )
                .termsOfService("My terms of service")
                .license(
                    License()
                        .name("My license")
                        .url("My license URL")
                )
        )
        .externalDocs(
            ExternalDocumentation()
                .description("My wiki page")
                .url("My wiki page URL")
        )

    private fun generateComponents() = Components().addSecuritySchemes(
        "authorization",
        generateSecurityScheme()
    )

    private fun generateSecurityScheme() = SecurityScheme().type(SecurityScheme.Type.HTTP)
        .scheme("Bearer")
        .description("Authorization token")
        .bearerFormat("JWT")

    private fun generateSecurityItem() = SecurityRequirement()
        .addList("authorization", listOf("read", "write"))
}
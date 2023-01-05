package com.dsumtsov.sku.controller

import com.dsumtsov.sku.IntegrationTestBase
import com.dsumtsov.sku.repository.SkuRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.reactive.server.WebTestClient

class SkuControllerTest : IntegrationTestBase() {

    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setup() {
        skuRepository.deleteAll()
    }

    @Test
    fun createSku_notFoundException() {
        webTestClient.get()
            .uri("/skus/1")
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
            .expectHeader().contentType(APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.path").isEqualTo("/skus/1")
            .jsonPath("$.message").isEqualTo("Not found sku with sku_id: 1")
    }
}
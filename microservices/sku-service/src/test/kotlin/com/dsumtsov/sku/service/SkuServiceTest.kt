package com.dsumtsov.sku.service

import com.dsumtsov.sku.IntegrationTestBase
import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.repository.SkuRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SkuServiceTest : IntegrationTestBase() {

    @Autowired
    private lateinit var skuRepository: SkuRepository

    @Autowired
    private lateinit var skuService: SkuService

    @BeforeEach
    fun setup() {
        skuRepository.deleteAll()
    }

    @Test
    fun createSku() {
        val skuDTO = SkuDTO(1, "name", 100)

        val created = skuService.createSku(skuDTO)

        assertEquals(skuDTO.skuId, created.skuId)
        assertEquals(skuDTO.name, created.name)
        assertEquals(skuDTO.weight, created.weight)
    }
}
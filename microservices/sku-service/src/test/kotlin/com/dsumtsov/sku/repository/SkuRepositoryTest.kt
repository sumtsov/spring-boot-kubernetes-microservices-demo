package com.dsumtsov.sku.repository

import com.dsumtsov.sku.IntegrationTestBase
import com.dsumtsov.sku.entity.Sku
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SkuRepositoryTest : IntegrationTestBase() {

    @Autowired
    private lateinit var repository: SkuRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun create() {
        val sku = Sku(
            id = null,
            version = null,
            skuId = 1,
            name = "test-1",
            weight = 100
        )
        repository.save(sku)
        val found = repository.findById(sku.id!!).get()

        assertEquals(sku.id, found.id);
        assertEquals(sku.version, found.version);
        assertEquals(sku.skuId, found.skuId);
        assertEquals(sku.name, found.name);
        assertEquals(sku.weight, found.weight);
        assertEquals(1, repository.count());
    }
}
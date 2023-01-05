package com.dsumtsov.sku.mapper

import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.entity.Sku
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class SkuMapperTest {

    private val mapper = Mappers.getMapper(SkuMapper::class.java)

    @Test
    fun toDto() {
        val sku = Sku("1", 2, 3, "name", 100)
        val skuDTO = mapper.toDto(sku)

        assertEquals(3, skuDTO.skuId)
        assertEquals("name", skuDTO.name)
        assertEquals(100, skuDTO.weight)
    }

    @Test
    fun toModel() {
        val skuDTO = SkuDTO(3, "name", 100);
        val sku = mapper.toModel(skuDTO);

        assertNull(sku.id);
        assertNull(sku.version);
        assertEquals(3, sku.skuId);
        assertEquals("name", sku.name);
        assertEquals(100, sku.weight);
    }
}
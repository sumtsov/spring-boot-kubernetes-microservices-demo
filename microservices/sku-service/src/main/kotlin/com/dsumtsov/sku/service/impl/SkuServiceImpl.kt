package com.dsumtsov.sku.service.impl

import com.dsumtsov.commons.exception.InvalidInputException
import com.dsumtsov.commons.exception.NotFoundException
import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.mapper.SkuMapper
import com.dsumtsov.sku.repository.SkuRepository
import com.dsumtsov.sku.service.SkuService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class SkuServiceImpl(
    private val skuRepository: SkuRepository,
    private val skuMapper: SkuMapper
) : SkuService {

    override fun createSku(skuDTO: SkuDTO): SkuDTO {
        if (skuDTO.skuId!! < 1) {
            throw InvalidInputException("Invalid sku_id: ${skuDTO.skuId}")
        }
        val sku = skuMapper.toModel(skuDTO)
        val savedSku = skuRepository.save(sku)
        return skuMapper.toDto(savedSku)
    }

    override fun getSku(skuId: Long): SkuDTO {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val sku = skuRepository.findBySkuId(skuId)
            ?: throw NotFoundException("Not found sku with sku_id: $skuId")
        return skuMapper.toDto(sku)
    }

    override fun deleteSku(skuId: Long) {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val sku = skuRepository.findBySkuId(skuId)
        if (sku != null) {
            skuRepository.delete(sku)
        } else {
            log.error { "Not found sku with sku_id: $skuId" }
        }
    }
}
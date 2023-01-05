package com.dsumtsov.sku.controller

import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.service.SkuService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/skus")
class SkuController(
    private val skuService: SkuService
) {

    @PostMapping
    fun createSku(@RequestBody skuDTO: SkuDTO): SkuDTO {
        log.info { "Request -> create sku; sku: $skuDTO" }
        return skuService.createSku(skuDTO)
    }

    @GetMapping("/{skuId}")
    fun getSku(@PathVariable skuId: Long): SkuDTO {
        log.info { "Request -> get sku by sku_id: $skuId" }
        return skuService.getSku(skuId)
    }

    @DeleteMapping("/{skuId}")
    fun deleteSku(@PathVariable skuId: Long) {
        log.info { "Request -> delete sku by sku_id: $skuId" }
        return skuService.deleteSku(skuId)
    }
}
package com.dsumtsov.sku.composite.client

import com.dsumtsov.sku.composite.dto.SkuDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(
    name = "sku",
    url = "\${client.endpoints.sku.url}"
)
interface SkuFeignClient {

    @PostMapping("/skus")
    fun createSku(@RequestBody skuDTO: SkuDTO): SkuDTO

    @GetMapping("/skus/{skuId}")
    fun getSku(@PathVariable skuId: Long): SkuDTO

    @DeleteMapping("/skus/{skuId}")
    fun deleteSku(@PathVariable skuId: Long)
}
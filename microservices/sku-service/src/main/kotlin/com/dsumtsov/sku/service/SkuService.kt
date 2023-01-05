package com.dsumtsov.sku.service

import com.dsumtsov.sku.dto.SkuDTO

interface SkuService {

    fun createSku(skuDTO: SkuDTO): SkuDTO

    fun getSku(skuId: Long): SkuDTO

    fun deleteSku(skuId: Long)
}
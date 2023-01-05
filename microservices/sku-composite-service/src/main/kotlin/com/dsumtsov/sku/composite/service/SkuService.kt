package com.dsumtsov.sku.composite.service

import com.dsumtsov.sku.composite.dto.SkuDTO

interface SkuService {

    fun createSku(skuDTO: SkuDTO): SkuDTO

    fun getSku(skuId: Long): SkuDTO

    fun deleteSku(skuId: Long)
}
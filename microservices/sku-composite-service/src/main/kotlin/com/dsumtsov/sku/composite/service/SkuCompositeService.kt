package com.dsumtsov.sku.composite.service

import com.dsumtsov.sku.composite.dto.SkuCompositeDTO

interface SkuCompositeService {

    fun createCompositeSku(skuCompositeDTO: SkuCompositeDTO): SkuCompositeDTO

    fun getCompositeSku(skuId: Long): SkuCompositeDTO

    fun deleteCompositeSku(skuId: Long)
}
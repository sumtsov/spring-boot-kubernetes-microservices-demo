package com.dsumtsov.sku.repository

import com.dsumtsov.sku.entity.Sku
import org.springframework.data.mongodb.repository.MongoRepository

interface SkuRepository : MongoRepository<Sku, String> {

    fun findBySkuId(skuId: Long): Sku?
}
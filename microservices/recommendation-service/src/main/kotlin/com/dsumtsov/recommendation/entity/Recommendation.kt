package com.dsumtsov.recommendation.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recommendation")
@CompoundIndex(
    name = "sku-rec-id",
    unique = true,
    def = "{'skuId': 1, 'recommendationId' : 1}"
)
data class Recommendation(
    @Id
    var id: String?,
    @Version
    var version: Long?,
    var skuId: Long,
    val recommendationId: Long?,
    var author: String,
    var rating: Int,
    var content: String
)

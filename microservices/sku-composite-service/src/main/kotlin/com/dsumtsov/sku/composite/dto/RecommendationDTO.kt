package com.dsumtsov.sku.composite.dto

data class RecommendationDTO(
    val recommendationId: Long?,
    val skuId: Long?,
    val author: String?,
    val rating: Long?,
    val content: String?
)

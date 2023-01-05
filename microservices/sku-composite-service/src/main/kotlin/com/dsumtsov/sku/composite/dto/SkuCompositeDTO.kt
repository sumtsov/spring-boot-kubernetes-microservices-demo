package com.dsumtsov.sku.composite.dto

data class SkuCompositeDTO(
    val skuId: Long?,
    val name: String?,
    val weight: Int?,
    val recommendations: List<RecommendationDTO>,
    val reviews: List<ReviewDTO>
)

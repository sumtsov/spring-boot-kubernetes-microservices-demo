package com.dsumtsov.recommendation.dto

import com.dsumtsov.commons.annotation.NoArg

@NoArg
data class RecommendationDTO(
    val recommendationId: Long?,
    val skuId: Long?,
    val author: String?,
    val rating: Int?,
    val content: String?
)

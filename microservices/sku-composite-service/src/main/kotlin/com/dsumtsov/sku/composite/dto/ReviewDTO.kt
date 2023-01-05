package com.dsumtsov.sku.composite.dto

data class ReviewDTO(
    val reviewId: Long?,
    val skuId: Long?,
    val author: String?,
    val subject: String?,
    val content: String?
)

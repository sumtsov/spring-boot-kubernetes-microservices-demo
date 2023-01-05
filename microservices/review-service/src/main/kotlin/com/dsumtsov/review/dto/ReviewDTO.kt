package com.dsumtsov.review.dto

import com.dsumtsov.commons.annotation.NoArg

@NoArg
data class ReviewDTO(
    val reviewId: Long?,
    val skuId: Long?,
    val author: String?,
    val subject: String?,
    val content: String?
)

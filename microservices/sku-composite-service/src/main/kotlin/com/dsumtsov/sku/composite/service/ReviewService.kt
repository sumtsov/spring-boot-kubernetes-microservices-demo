package com.dsumtsov.sku.composite.service

import com.dsumtsov.sku.composite.dto.ReviewDTO

interface ReviewService {

    fun createReview(reviewDTO: ReviewDTO): ReviewDTO

    fun getReviews(skuId: Long): List<ReviewDTO>

    fun deleteReviews(skuId: Long)
}
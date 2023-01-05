package com.dsumtsov.review.service

import com.dsumtsov.review.dto.ReviewDTO

interface ReviewService {

    fun createReview(reviewDTO: ReviewDTO): ReviewDTO

    fun getReviews(skuId: Long): List<ReviewDTO>

    fun deleteReviews(skuId: Long)
}
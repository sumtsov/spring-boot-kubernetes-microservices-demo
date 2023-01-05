package com.dsumtsov.review.repository

import com.dsumtsov.review.entity.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {

    fun findAllBySkuId(skuId: Long): List<Review>
}
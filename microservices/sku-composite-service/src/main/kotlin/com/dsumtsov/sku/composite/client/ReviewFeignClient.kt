package com.dsumtsov.sku.composite.client

import com.dsumtsov.sku.composite.dto.ReviewDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "review",
    url = "\${client.endpoints.review.url}"
)
interface ReviewFeignClient {

    @PostMapping("/reviews")
    fun createReview(reviewDTO: ReviewDTO): ReviewDTO

    @GetMapping("/reviews/{skuId}")
    fun getReviews(@PathVariable skuId: Long): List<ReviewDTO>

    @DeleteMapping("/reviews/{skuId}")
    fun deleteReviews(@PathVariable skuId: Long)
}
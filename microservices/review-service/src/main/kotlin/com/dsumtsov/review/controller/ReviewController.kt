package com.dsumtsov.review.controller

import com.dsumtsov.review.dto.ReviewDTO
import com.dsumtsov.review.service.ReviewService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping
    fun createReview(@RequestBody reviewDTO: ReviewDTO): ReviewDTO {
        log.info { "Request -> create review; review: $reviewDTO" }
        return reviewService.createReview(reviewDTO)
    }

    @GetMapping("/{skuId}")
    fun getReviews(@PathVariable skuId: Long): List<ReviewDTO> {
        log.info { "Request -> get reviews by sku_id: $skuId" }
        return reviewService.getReviews(skuId)
    }

    @DeleteMapping("/{skuId}")
    fun deleteReviews(@PathVariable skuId: Long) {
        log.info { "Request -> delete reviews by sku_id: $skuId" }
        return reviewService.deleteReviews(skuId)
    }
}
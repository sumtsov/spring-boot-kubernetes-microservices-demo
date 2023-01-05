package com.dsumtsov.review.service.impl

import com.dsumtsov.commons.exception.InvalidInputException
import com.dsumtsov.review.dto.ReviewDTO
import com.dsumtsov.review.mapper.ReviewMapper
import com.dsumtsov.review.repository.ReviewRepository
import com.dsumtsov.review.service.ReviewService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val reviewMapper: ReviewMapper
) : ReviewService {

    override fun createReview(reviewDTO: ReviewDTO): ReviewDTO {
        if (reviewDTO.skuId!! < 1) {
            throw InvalidInputException("Invalid sku_id: ${reviewDTO.skuId}")
        }
        val review = reviewMapper.toModel(reviewDTO)
        val savedReview = reviewRepository.save(review)
        log.info { "Created review with id: ${savedReview.id}" }
        return reviewMapper.toDto(savedReview)
    }

    override fun getReviews(skuId: Long): List<ReviewDTO> {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val reviews = reviewRepository.findAllBySkuId(skuId)
        log.info { "Found ${reviews.size} reviews by sku_id: $skuId" }
        return reviewMapper.modelListToDtoList(reviews)
    }

    override fun deleteReviews(skuId: Long) {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val reviews = reviewRepository.findAllBySkuId(skuId)
        log.info { "Deleting ${reviews.size} reviews" }
        reviewRepository.deleteAll(reviews)
    }
}
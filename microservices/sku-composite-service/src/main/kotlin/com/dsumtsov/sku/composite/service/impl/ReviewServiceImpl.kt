package com.dsumtsov.sku.composite.service.impl

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.composite.client.ReviewFeignClient
import com.dsumtsov.sku.composite.dto.ReviewDTO
import com.dsumtsov.sku.composite.kafka.KafkaProducer
import com.dsumtsov.sku.composite.service.ReviewService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class ReviewServiceImpl(
    private val reviewFeignClient: ReviewFeignClient,
    private val kafkaProducer: KafkaProducer
) : ReviewService {

    @Value("\${kafka.topic.review}")
    lateinit var topic: String

    override fun createReview(reviewDTO: ReviewDTO): ReviewDTO {
        log.info { "Trying to create review: $reviewDTO" }
        kafkaProducer.send(
            Event(
                type = Event.Type.CREATE,
                key = reviewDTO.skuId,
                data = reviewDTO
            ),
            topic)
        return reviewDTO
    }

    override fun getReviews(skuId: Long): List<ReviewDTO> {
        log.info { "Trying to get reviews by sku_id: $skuId" }
        return try {
            reviewFeignClient.getReviews(skuId)
        } catch (ex: Exception) {
            log.error { "Failed to get reviews by sku_id: ${ex.message}" }
            listOf()
        }
    }

    override fun deleteReviews(skuId: Long) {
        log.info { "Trying to delete reviews by sku_id: $skuId" }
        kafkaProducer.send(
            Event(
                type = Event.Type.DELETE,
                key = skuId
            ),
            topic)
    }
}
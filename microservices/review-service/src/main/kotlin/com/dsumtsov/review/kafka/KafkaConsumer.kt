package com.dsumtsov.review.kafka

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.review.dto.ReviewDTO
import com.dsumtsov.review.service.ReviewService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KafkaConsumer(
    val reviewService: ReviewService
) {

    @KafkaListener(
        topics = ["\${kafka.topic.review}"]
    )
    fun consume(event: Event<ReviewDTO>) {
        log.info { "Received Kafka event: $event" }
        when (event.type) {
            Event.Type.CREATE -> reviewService.createReview(event.data!!)
            Event.Type.DELETE -> reviewService.deleteReviews(event.key!!)
            else -> log.warn { "Received event with unknown type: ${event.type}" }
        }
    }
}
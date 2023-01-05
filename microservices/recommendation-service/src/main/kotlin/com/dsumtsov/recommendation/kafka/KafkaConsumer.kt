package com.dsumtsov.recommendation.kafka

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.recommendation.dto.RecommendationDTO
import com.dsumtsov.recommendation.service.RecommendationService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KafkaConsumer(
    val recommendationService: RecommendationService
) {

    @KafkaListener(
        topics = ["\${kafka.topic.recommendation}"]
    )
    fun consume(event: Event<RecommendationDTO>) {
        log.info { "Received Kafka event: $event" }
        when (event.type) {
            Event.Type.CREATE -> recommendationService.createRecommendation(event.data!!)
            Event.Type.DELETE -> recommendationService.deleteRecommendations(event.key!!)
            else -> log.warn { "Received event with unknown type: ${event.type}" }
        }
    }
}
package com.dsumtsov.sku.composite.service.impl

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.composite.client.RecommendationFeignClient
import com.dsumtsov.sku.composite.dto.RecommendationDTO
import com.dsumtsov.sku.composite.kafka.KafkaProducer
import com.dsumtsov.sku.composite.service.RecommendationService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class RecommendationServiceImpl(
    private val recommendationFeignClient: RecommendationFeignClient,
    private val kafkaProducer: KafkaProducer
) : RecommendationService {

    @Value("\${kafka.topic.recommendation}")
    lateinit var topic: String

    override fun createRecommendation(recommendationDTO: RecommendationDTO): RecommendationDTO {
        log.info { "Trying to create recommendation: $recommendationDTO" }
        kafkaProducer.send(
            Event(
                type = Event.Type.CREATE,
                key = recommendationDTO.skuId,
                data = recommendationDTO
            ),
            topic)
        return recommendationDTO;
    }

    override fun getRecommendations(skuId: Long): List<RecommendationDTO> {
        log.info { "Trying to get recommendations by sku_id: $skuId" }
        return try {
            recommendationFeignClient.getRecommendations(skuId)
        } catch(ex: Exception) {
            log.error { "Failed to get recommendations by sku_id: ${ex.message}" }
            listOf()
        }
    }

    override fun deleteRecommendations(skuId: Long) {
        log.info { "Trying to delete recommendations by sku_id: $skuId" }
        kafkaProducer.send(
            Event(
                type = Event.Type.DELETE,
                key = skuId
            ),
            topic)
    }
}
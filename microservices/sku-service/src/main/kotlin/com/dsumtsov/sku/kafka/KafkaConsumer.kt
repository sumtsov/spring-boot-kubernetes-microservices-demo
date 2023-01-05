package com.dsumtsov.sku.kafka

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.service.SkuService
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KafkaConsumer(
    val skuService: SkuService
) {

    @KafkaListener(
        topics = ["\${kafka.topic.sku}"]
    )
    fun consume(event: Event<SkuDTO>) {
        log.info { "Received Kafka event: $event" }
        when (event.type) {
            Event.Type.CREATE -> skuService.createSku(event.data!!)
            Event.Type.DELETE -> skuService.deleteSku(event.key!!)
            else -> log.warn { "Received event with unknown type: ${event.type}" }
        }
    }
}
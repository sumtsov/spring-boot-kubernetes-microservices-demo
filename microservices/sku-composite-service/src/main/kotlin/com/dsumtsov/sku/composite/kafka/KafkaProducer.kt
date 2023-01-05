package com.dsumtsov.sku.composite.kafka

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.commons.exception.KafkaException
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Event<Any>>
) {

    fun send(event: Event<Any>, topic: String) {
        log.info { "Sending event to Kafka; event: $event, topic: $topic" }
        val completableFuture = kafkaTemplate.send(topic, event.key.toString(), event)
        completableFuture.whenComplete { result, ex ->
            if (ex != null) {
                log.error {
                    "Failed to send event to Kafka: ${ex.message}"
                }
                throw KafkaException(ex.message)
            } else {
                log.info {
                    "Event successfully sent to Kafka: ${result?.producerRecord?.value()}"
                }
            }
        }
    }
}
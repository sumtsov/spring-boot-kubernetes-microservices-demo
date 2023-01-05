package com.dsumtsov.sku.config

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.dto.SkuDTO
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import mu.KotlinLogging
import org.apache.kafka.common.serialization.Deserializer

private val log = KotlinLogging.logger {}

class KafkaEventDeserializer : Deserializer<Event<SkuDTO>> {

    override fun deserialize(topic: String?, data: ByteArray?): Event<SkuDTO> {
        return try {
            ObjectMapper().registerModule(JavaTimeModule())
                .readValue(data, object: TypeReference<Event<SkuDTO>>() {})
        } catch (e: Throwable) {
            log.error { "Failed to deserialize event: ${e.message}" }
            throw e
        }
    }
}
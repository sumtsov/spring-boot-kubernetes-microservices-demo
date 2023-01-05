package com.dsumtsov.sku.composite.config

import com.dsumtsov.commons.dto.kafka.Event
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfiguration {

    @Value("\${kafka.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Event<Any>> {
        val config: MutableMap<String, Any?> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Event<Any>> {
        return KafkaTemplate(producerFactory())
    }
}
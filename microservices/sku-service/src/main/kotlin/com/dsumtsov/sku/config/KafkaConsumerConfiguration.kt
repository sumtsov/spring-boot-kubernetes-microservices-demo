package com.dsumtsov.sku.config

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.dto.SkuDTO
import org.springframework.context.annotation.Configuration
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

import java.util.HashMap

@Configuration
class KafkaConsumerConfiguration {

    @Value("\${kafka.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Event<SkuDTO>> {
        val config: MutableMap<String, Any?> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ConsumerConfig.GROUP_ID_CONFIG] = "group-id"
        config[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaEventDeserializer::class.java
        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Event<SkuDTO>> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, Event<SkuDTO>> =
            ConcurrentKafkaListenerContainerFactory<String, Event<SkuDTO>>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
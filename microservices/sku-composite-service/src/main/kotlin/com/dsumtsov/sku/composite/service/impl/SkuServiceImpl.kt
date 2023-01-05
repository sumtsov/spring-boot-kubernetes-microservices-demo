package com.dsumtsov.sku.composite.service.impl

import com.dsumtsov.commons.dto.kafka.Event
import com.dsumtsov.sku.composite.client.SkuFeignClient
import com.dsumtsov.sku.composite.dto.SkuDTO
import com.dsumtsov.sku.composite.kafka.KafkaProducer
import com.dsumtsov.sku.composite.service.SkuService
import com.dsumtsov.sku.composite.util.ExceptionUtils
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class SkuServiceImpl(
    private val skuFeignClient: SkuFeignClient,
    private val kafkaProducer: KafkaProducer
) : SkuService {

    @Value("\${kafka.topic.sku}")
    lateinit var topic: String

    override fun createSku(skuDTO: SkuDTO): SkuDTO {
        log.info { "Trying to create sku: $skuDTO" }
        kafkaProducer.send(
            Event(
                type = Event.Type.CREATE,
                key = skuDTO.skuId,
                data = skuDTO
            ),
            topic)
        return skuDTO
    }

    override fun getSku(skuId: Long): SkuDTO {
        log.info { "Trying to get sku by sku_id: $skuId" }
        return try {
            skuFeignClient.getSku(skuId)
        } catch (ex: Exception) {
            log.error { "Failed to get sku by sku_id: ${ex.message}" }
            throw ExceptionUtils.handleFeignException(ex)
        }
    }

    override fun deleteSku(skuId: Long) {
        log.info { "Trying to delete sku by sku_id: $skuId" }
        kafkaProducer.send(
            Event(
                type = Event.Type.DELETE,
                key = skuId
            ),
            topic)
    }
}
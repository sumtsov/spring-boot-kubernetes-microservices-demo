package com.dsumtsov.sku.composite.service.impl

import com.dsumtsov.sku.composite.dto.SkuCompositeDTO
import com.dsumtsov.sku.composite.dto.SkuDTO
import com.dsumtsov.sku.composite.service.RecommendationService
import com.dsumtsov.sku.composite.service.ReviewService
import com.dsumtsov.sku.composite.service.SkuCompositeService
import com.dsumtsov.sku.composite.service.SkuService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class SkuCompositeServiceImpl(
    private val recommendationService: RecommendationService,
    private val reviewService: ReviewService,
    private val skuService: SkuService
) : SkuCompositeService {

    override fun createCompositeSku(skuCompositeDTO: SkuCompositeDTO): SkuCompositeDTO {
        val skuDTO = SkuDTO(
            skuCompositeDTO.skuId,
            skuCompositeDTO.name,
            skuCompositeDTO.weight
        )

        try {
            skuService.createSku(skuDTO)

            // TODO: create batch API
            skuCompositeDTO.recommendations.forEach {
                recommendationService.createRecommendation(it)
            }
            // TODO: create batch API
            skuCompositeDTO.reviews.forEach {
                reviewService.createReview(it)
            }
        } catch (ex: Exception) {
            log.error { "Failed to create composite sku: ${ex.message}" }
            throw ex
        }

        log.info { "Successfully created composite sku: $skuCompositeDTO" }

        return skuCompositeDTO
    }

    override fun getCompositeSku(skuId: Long): SkuCompositeDTO {
        val skuDTO = skuService.getSku(skuId)
        val recommendationDTOs = recommendationService.getRecommendations(skuId)
        val reviewDTOs = reviewService.getReviews(skuId)

        return SkuCompositeDTO(
            skuDTO.skuId,
            skuDTO.name,
            skuDTO.weight,
            recommendationDTOs,
            reviewDTOs
        )
    }

    override fun deleteCompositeSku(skuId: Long) {
        try {
            skuService.deleteSku(skuId)
            reviewService.deleteReviews(skuId)
            recommendationService.deleteRecommendations(skuId)
        } catch (ex: Exception) {
            log.error { "Failed to delete composite sku: ${ex.message}" }
            throw ex
        }

        log.info { "Successfully deleted composite sku by sku_id: $skuId" }
    }
}
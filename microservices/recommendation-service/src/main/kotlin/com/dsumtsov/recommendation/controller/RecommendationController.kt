package com.dsumtsov.recommendation.controller

import com.dsumtsov.recommendation.dto.RecommendationDTO
import com.dsumtsov.recommendation.service.RecommendationService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/recommendations")
class RecommendationController(
    private val recommendationService: RecommendationService
) {

    @PostMapping
    fun createRecommendation(@RequestBody recommendationDTO: RecommendationDTO): RecommendationDTO {
        log.info { "Request -> create recommendation; recommendation: $recommendationDTO" }
        return recommendationService.createRecommendation(recommendationDTO)
    }

    @GetMapping("/{skuId}")
    fun getRecommendations(@PathVariable skuId: Long): List<RecommendationDTO> {
        log.info { "Request -> get recommendations by sku_id: $skuId" }
        return recommendationService.getRecommendations(skuId)
    }

    @DeleteMapping("/{skuId}")
    fun deleteRecommendations(@PathVariable skuId: Long) {
        log.info { "Request -> delete recommendations by sku_id: $skuId" }
        return recommendationService.deleteRecommendations(skuId)
    }
}
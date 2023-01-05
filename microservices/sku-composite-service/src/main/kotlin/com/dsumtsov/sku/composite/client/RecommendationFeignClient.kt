package com.dsumtsov.sku.composite.client

import com.dsumtsov.sku.composite.dto.RecommendationDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "recommendation",
    url = "\${client.endpoints.recommendation.url}"
)
interface RecommendationFeignClient {

    @PostMapping("/recommendations")
    fun createRecommendation(recommendationDTO: RecommendationDTO): RecommendationDTO

    @GetMapping("/recommendations/{skuId}")
    fun getRecommendations(@PathVariable skuId: Long): List<RecommendationDTO>

    @DeleteMapping("/recommendations/{skuId}")
    fun deleteRecommendations(@PathVariable skuId: Long)
}
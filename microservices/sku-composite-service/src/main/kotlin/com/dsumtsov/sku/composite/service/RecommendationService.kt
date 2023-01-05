package com.dsumtsov.sku.composite.service

import com.dsumtsov.sku.composite.dto.RecommendationDTO

interface RecommendationService {

    fun createRecommendation(recommendationDTO: RecommendationDTO): RecommendationDTO

    fun getRecommendations(skuId: Long): List<RecommendationDTO>

    fun deleteRecommendations(skuId: Long)
}
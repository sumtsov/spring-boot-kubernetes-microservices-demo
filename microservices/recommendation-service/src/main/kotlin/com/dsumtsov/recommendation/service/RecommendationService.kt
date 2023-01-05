package com.dsumtsov.recommendation.service

import com.dsumtsov.recommendation.dto.RecommendationDTO

interface RecommendationService {

    fun createRecommendation(recommendationDTO: RecommendationDTO): RecommendationDTO

    fun getRecommendations(skuId: Long): List<RecommendationDTO>

    fun deleteRecommendations(skuId: Long)
}
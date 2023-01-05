package com.dsumtsov.recommendation.repository

import com.dsumtsov.recommendation.entity.Recommendation
import org.springframework.data.mongodb.repository.MongoRepository

interface RecommendationRepository : MongoRepository<Recommendation, String> {

    fun findAllBySkuId(skuId: Long): List<Recommendation>
}
package com.dsumtsov.recommendation.service.impl

import com.dsumtsov.commons.exception.InvalidInputException
import com.dsumtsov.recommendation.repository.RecommendationRepository
import com.dsumtsov.recommendation.dto.RecommendationDTO
import com.dsumtsov.recommendation.mapper.RecommendationMapper
import com.dsumtsov.recommendation.service.RecommendationService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class RecommendationServiceImpl(
    private val recommendationRepository: RecommendationRepository,
    private val recommendationMapper: RecommendationMapper
) : RecommendationService {

    override fun createRecommendation(recommendationDTO: RecommendationDTO): RecommendationDTO {
        if (recommendationDTO.skuId!! < 1) {
            throw InvalidInputException("Invalid sku_id: ${recommendationDTO.skuId}")
        }
        val recommendation = recommendationMapper.toModel(recommendationDTO)
        val savedRecommendation = recommendationRepository.save(recommendation)
        return recommendationMapper.toDto(savedRecommendation)
    }

    override fun getRecommendations(skuId: Long): List<RecommendationDTO> {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val recommendations = recommendationRepository.findAllBySkuId(skuId)
        return recommendationMapper.modelListToDtoList(recommendations)
    }

    override fun deleteRecommendations(skuId: Long) {
        if (skuId < 1) {
            throw InvalidInputException("Invalid sku_id: $skuId")
        }
        val recommendations = recommendationRepository.findAllBySkuId(skuId)
        recommendationRepository.deleteAll(recommendations)
    }
}
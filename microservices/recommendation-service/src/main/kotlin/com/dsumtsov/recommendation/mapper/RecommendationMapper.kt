package com.dsumtsov.recommendation.mapper

import com.dsumtsov.recommendation.dto.RecommendationDTO
import com.dsumtsov.recommendation.entity.Recommendation
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface RecommendationMapper {

    fun toDto(model: Recommendation): RecommendationDTO

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "version", ignore = true)
    )
    fun toModel(dto: RecommendationDTO): Recommendation

    fun modelListToDtoList(modelList: List<Recommendation>): List<RecommendationDTO>

    fun dtoListToModelList(dtoList: List<RecommendationDTO>): List<Recommendation>
}
package com.dsumtsov.review.mapper

import com.dsumtsov.review.dto.ReviewDTO
import com.dsumtsov.review.entity.Review
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ReviewMapper {

    fun toDto(model: Review): ReviewDTO

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "version", ignore = true)
    )
    fun toModel(dto: ReviewDTO): Review

    fun modelListToDtoList(modelList: List<Review>): List<ReviewDTO>

    fun dtoListToModelList(dtoList: List<ReviewDTO>): List<Review>
}
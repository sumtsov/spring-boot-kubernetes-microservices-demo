package com.dsumtsov.sku.mapper

import com.dsumtsov.sku.dto.SkuDTO
import com.dsumtsov.sku.entity.Sku
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface SkuMapper {

    fun toDto(model: Sku): SkuDTO

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "version", ignore = true)
    )
    fun toModel(dto: SkuDTO): Sku
}
package com.dsumtsov.sku.dto

import com.dsumtsov.commons.annotation.NoArg

@NoArg
data class SkuDTO(
    val skuId: Long?,
    val name: String?,
    val weight: Int?
)

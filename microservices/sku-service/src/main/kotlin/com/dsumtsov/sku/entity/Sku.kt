package com.dsumtsov.sku.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sku")
data class Sku(
    @Id
    var id: String?,
    @Version
    var version: Long?,
    @Indexed(unique = true)
    var skuId: Long,
    var name: String,
    var weight: Int
)

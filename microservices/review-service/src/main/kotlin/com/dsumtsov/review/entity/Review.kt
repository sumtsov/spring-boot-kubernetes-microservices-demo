package com.dsumtsov.review.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "review",
    indexes = [
        Index(
            name = "review_unique_idx",
            unique = true,
            columnList = "skuId,reviewId"
        )
    ]
)
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Version
    var version: Long?,
    var skuId: Long,
    var reviewId: Long,
    var author: String,
    var subject: String,
    var content: String
)

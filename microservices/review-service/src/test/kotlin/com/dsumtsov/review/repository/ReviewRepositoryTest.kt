package com.dsumtsov.review.repository

import com.dsumtsov.review.IntegrationTestBase
import com.dsumtsov.review.entity.Review
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ReviewRepositoryTest : IntegrationTestBase() {

    @Autowired
    private lateinit var repository: ReviewRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
        val review = Review(
            id = null,
            version = null,
            skuId = 1,
            reviewId = 2,
            author = "a",
            subject = "s",
            content = "c"
        )
        repository.save(review)
    }

    @Test
    fun create() {
        val review = Review(
            id = null,
            version = null,
            skuId = 1,
            reviewId = 3,
            author = "a",
            subject = "s",
            content = "c"
        )
        repository.save(review)
        val found = repository.findById(review.id!!).get()

        assertEquals(review.id, found.id)
        assertEquals(review.version, found.version)
        assertEquals(review.skuId, found.skuId)
        assertEquals(review.reviewId, found.reviewId)
        assertEquals(review.author, found.author)
        assertEquals(review.subject, found.subject)
        assertEquals(review.content, found.content)
        assertEquals(2, repository.count())
    }

}
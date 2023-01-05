package com.dsumtsov.recommendation.repository

import com.dsumtsov.recommendation.IntegrationTestBase
import com.dsumtsov.recommendation.entity.Recommendation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class RecommendationRepositoryTest : IntegrationTestBase() {

    @Autowired
    private lateinit var repository: RecommendationRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
        val recommendation = Recommendation(
            id = null,
            version = null,
            skuId = 1,
            recommendationId = 1,
            author = "a",
            rating = 10,
            content = "c"
        )
        repository.save(recommendation)
    }

    @Test
    fun create() {
        val recommendation = Recommendation(
            id = null,
            version = null,
            skuId = 2,
            recommendationId = 2,
            author = "author-2",
            rating = 2,
            content = "test-content"
        )
        repository.save(recommendation)
        val found = repository.findById(recommendation.id!!).get()

        assertEquals(recommendation.id, found.id)
        assertEquals(recommendation.version, found.version)
        assertEquals(recommendation.skuId, found.skuId)
        assertEquals(recommendation.recommendationId, found.recommendationId)
        assertEquals(recommendation.author, found.author)
        assertEquals(recommendation.rating, found.rating)
        assertEquals(recommendation.content, found.content)
        assertEquals(2, repository.count())
    }
}
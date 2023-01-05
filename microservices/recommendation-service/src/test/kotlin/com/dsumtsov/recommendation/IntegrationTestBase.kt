package com.dsumtsov.recommendation

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer

@ActiveProfiles("test")
@SpringBootTest
class IntegrationTestBase {

    companion object {

        private val database = MongoDBContainer("mongo:4.4.2")

        init {
            database.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun databaseProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.host", database::getContainerIpAddress)
            registry.add("spring.data.mongodb.port") { database.getMappedPort(27017) }
            registry.add("spring.data.mongodb.database") { "sku" }
        }
    }
}
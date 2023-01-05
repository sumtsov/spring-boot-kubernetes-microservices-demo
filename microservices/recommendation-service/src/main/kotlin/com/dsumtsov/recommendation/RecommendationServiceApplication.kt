package com.dsumtsov.recommendation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.dsumtsov")
class RecommendationServiceApplication

fun main(args: Array<String>) {
    runApplication<RecommendationServiceApplication>(*args)
}

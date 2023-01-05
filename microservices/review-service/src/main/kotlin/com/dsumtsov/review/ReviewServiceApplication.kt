package com.dsumtsov.review

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.dsumtsov")
class ReviewServiceApplication

fun main(args: Array<String>) {
    runApplication<ReviewServiceApplication>(*args)
}

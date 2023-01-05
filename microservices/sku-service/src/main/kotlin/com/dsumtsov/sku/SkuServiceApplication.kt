package com.dsumtsov.sku

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.dsumtsov")
class SkuServiceApplication

fun main(args: Array<String>) {
    runApplication<SkuServiceApplication>(*args)
}

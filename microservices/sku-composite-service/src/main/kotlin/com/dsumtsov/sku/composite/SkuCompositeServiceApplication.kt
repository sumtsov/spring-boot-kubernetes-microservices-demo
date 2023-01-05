package com.dsumtsov.sku.composite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SkuCompositeServiceApplication

fun main(args: Array<String>) {
    runApplication<SkuCompositeServiceApplication>(*args)
}

package com.giggle.webclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication(scanBasePackages = ["com.giggle"])
@EnableAsync
class WebClientApplication

fun main(args: Array<String>) {
    runApplication<WebClientApplication>(*args)
}
package com.giggle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication(scanBasePackages = ["com.giggle"])
@EnableAsync
class WebsocketApplication

fun main(args: Array<String>) {
    runApplication<WebsocketApplication>(*args)
}
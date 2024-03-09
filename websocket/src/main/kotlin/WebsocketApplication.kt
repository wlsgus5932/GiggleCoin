package com.giggle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.giggle"])
class WebsocketApplication

fun main(args: Array<String>) {
    runApplication<WebsocketApplication>(*args)
}
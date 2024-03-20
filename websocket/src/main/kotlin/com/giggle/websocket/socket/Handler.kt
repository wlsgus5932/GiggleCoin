package com.giggle.websocket.socket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class Handler {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun handleHelloMessage(message: String): String {
        println("Received message: $message")

        val responseMessage = "Echo: $message"
        println("Sent message: $responseMessage")
        return responseMessage
    }
}
package com.giggle.websocket.socket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class Handler {

    @MessageMapping("/hello") // 클라이언트로부터 "/hello" 메시지를 받으면 이 메서드를 호출합니다.
    @SendTo("/topic/greetings") // "/topic/greetings" 주제를 구독하는 클라이언트에게 응답 메시지를 전송합니다.
    fun handleHelloMessage(message: String): String {
        println("Received message: $message")

        // 클라이언트에게 응답 메시지를 보냅니다.
        val responseMessage = "Echo: $message"
        println("Sent message: $responseMessage")
        return responseMessage
    }
}
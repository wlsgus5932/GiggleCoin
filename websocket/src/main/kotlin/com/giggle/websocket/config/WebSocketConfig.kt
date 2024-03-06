package com.giggle.websocket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.config.annotation.EnableWebSocket

@Configuration
@EnableWebSocket
class WebSocketConfig {

    @Bean
    fun customWebSocketClient(): StandardWebSocketClient {
        return StandardWebSocketClient()
    }
}
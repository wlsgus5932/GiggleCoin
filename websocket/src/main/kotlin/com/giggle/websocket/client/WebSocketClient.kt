package com.giggle.websocket.client

import com.giggle.websocket.handler.BithumbHandler
import com.giggle.websocket.handler.CoinOneHandler
import jakarta.annotation.PostConstruct
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.socket.client.WebSocketConnectionManager
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketClient(
    private val bithumbHandler: BithumbHandler,
    private val coinOneHandler: CoinOneHandler
) {

    @PostConstruct
    fun connect() {
        println("진입")
        connectToWebSocket("wss://pubwss.bithumb.com/pub/ws", bithumbHandler)
        connectToWebSocket("wss://stream.coinone.co.kr", coinOneHandler)
        //TODO: upbit websocket 추가
    }

    @Async
    fun connectToWebSocket(url: String, handler: TextWebSocketHandler) {
        println("connect 진입")
        val manager =
            WebSocketConnectionManager(
                StandardWebSocketClient(),
                handler,
                url
            )
        manager.isAutoStartup = true
        manager.start()
    }
}
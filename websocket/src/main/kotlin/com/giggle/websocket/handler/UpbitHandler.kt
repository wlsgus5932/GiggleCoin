package com.giggle.websocket.handler

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

//TODO: upbit logic 구현
class UpbitHandler : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // 업비트 웹소켓 메시지 처리 로직
        println("BTC Message: ${message.payload}")
    }
}

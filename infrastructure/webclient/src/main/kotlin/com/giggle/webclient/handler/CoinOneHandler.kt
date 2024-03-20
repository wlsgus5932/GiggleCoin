package com.giggle.com.giggle.websocket.handler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.domain.coinone.request.CoinOneSocket
import com.giggle.com.giggle.websocket.log.Logger
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class CoinOneHandler : TextWebSocketHandler() {
    private val logger = Logger()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val response =
            CoinOneSocket.createCoinOneSocket(
                CoinOneSocket.CoinOneTopic(
                    quoteCurrency = "KRW",
                    targetCurrency = "BTC"
                )
            )

        val subscribeMessage =
            jacksonObjectMapper().writeValueAsString(response)

        session.sendMessage(TextMessage(subscribeMessage))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        //TODO: coinone 응답 값 수신 후 Logic 처리
        val messageMap: Map<String, Any> = jacksonObjectMapper().readValue(message.payload)
        val test: Map<String, Any> = messageMap["data"] as Map<String, Any>
        logger.info("coinone -> ${test["last"]}")
    }
}

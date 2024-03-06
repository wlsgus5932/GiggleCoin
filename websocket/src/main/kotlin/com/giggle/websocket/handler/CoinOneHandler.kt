package com.giggle.websocket.handler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.websocket.dto.request.coinone.CoinOneParameter
import com.giggle.websocket.dto.request.coinone.CoinOneRequest
import com.giggle.websocket.log.Logger
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class CoinOneHandler : TextWebSocketHandler() {
    private val logger = Logger()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val subscribeMessage = CoinOneRequest.of(
            CoinOneParameter.RequestType.SUBSCRIBE.value,
            CoinOneParameter.Channel.TICKER.value,
            CoinOneRequest.Topic(
                CoinOneParameter.QuoteCurrency.KRW.value,
                CoinOneParameter.TargetCurrency.BTC.value
            )
        )

        session.sendMessage(TextMessage(subscribeMessage))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        //TODO: coinone 응답 값 수신 후 Logic 처리
        val messageMap: Map<String, Any> = jacksonObjectMapper().readValue(message.payload)
        val test: Map<String, Any> = messageMap["data"] as Map<String, Any>
//        logger.info("coinone -> ${test["last"]}")
    }
}

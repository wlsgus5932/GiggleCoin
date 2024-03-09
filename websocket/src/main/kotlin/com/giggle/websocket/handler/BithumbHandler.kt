package com.giggle.websocket.handler

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.websocket.dto.request.bithumb.BithumbParameter
import com.giggle.websocket.dto.request.bithumb.BithumbRequest
import com.giggle.websocket.dto.response.bithumb.BithumbTickerResponse
import com.giggle.websocket.log.Logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*

@Component
class BithumbHandler(
    private val kafkaTemplate: KafkaTemplate<String, String>

): TextWebSocketHandler() {
    private val logger = Logger()
    private var responseCheck = false
    private var timer: Timer? = null

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val subscribeMessage =
            BithumbRequest.of(
                BithumbParameter.Type.TICKER.value,
                listOf(
                    BithumbParameter.Symbols.BTC_KRW.value,
                    BithumbParameter.Symbols.ETH_KRW.value
                ),
                listOf(
                    BithumbParameter.TickTypes.THIRTY_MINUTES.value
                )
            )

            session.sendMessage(TextMessage(subscribeMessage))
    }

    /*
    * FIXME:
    *  Bithumb WebSocket api 사용 시
    *  잘못된 파라미터를 보내도 연결이 성공하고, 연결 성공 응답 값도 수신 되지만
    *  잘못된 파라미터를 받았다는 에러 메세지를 따로 응답해주지 않음.
    *  연결 성공 응답값을 받고 5초안에 코인 관련된 응답 값이 없다면 세션 종료하는 로직 추가.
    *
    * */
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val responseTree = jacksonObjectMapper().readTree(message.payload)

        when (responseTree.has("status")) {
            true -> extracted(session) //응답 데이터에 status 속성이 있다면 5초동안 응답 수신 체크
            false -> {
                try {
                    responseCheck = true
                    kafkaTemplate.send("bithumb", message.payload)
                    val ticker: BithumbTickerResponse = jacksonObjectMapper().readValue(message.payload)
                    logger.info("bithumb -> ${ticker.content.closePrice} ${ticker.content.symbol}")

                } catch (e: JsonMappingException) {
                    logger.error(e.message)
                }
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        //TODO: 세션 종료 후 로직
    }

    private fun extracted(session: WebSocketSession) {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                if (!responseCheck) {
                    timer?.cancel()
                    session.close()
                    logger.error("No response from Bithumb WebSocket Server")
                } else {
                    timer?.cancel()
                }
            }
        }, 5000) // 소켓 최초 연결 후 5초안에 코인 관련 응답 값이 없다면 클라이언트 세션 종료
    }
}
package com.giggle.consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.websocket.dto.response.bithumb.BithumbTickerResponse
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
) {

    @KafkaListener(topics = ["bithumb"], groupId = "bithumb-group")
    fun listen(message: String) {
        val result: BithumbTickerResponse = jacksonObjectMapper().readValue(message)
        println("recieved: $result")
    }
}
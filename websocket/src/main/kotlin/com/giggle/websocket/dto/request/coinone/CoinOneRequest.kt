package com.giggle.websocket.dto.request.coinone

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

//TODO: bithumb 구현 완료 후 domain 모듈로 이동
data class CoinOneRequest(
    @JsonProperty("request_type")
    val requestType: String,

    @JsonProperty("channel")
    val channel: String,

    @JsonProperty("topic")
    val topic: Topic
) {
    data class Topic(
        @JsonProperty("quote_currency")
        val quoteCurrency: String,

        @JsonProperty("target_currency")
        val targetCurrency: String
    )

    companion object {
        fun of(resultType: String, channel: String, topic: Topic): String {
            return jacksonObjectMapper()
                .writeValueAsString(
                    CoinOneRequest(resultType, channel, topic)
                )
        }
    }
}



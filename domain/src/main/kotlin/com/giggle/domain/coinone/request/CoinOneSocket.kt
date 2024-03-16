package com.giggle.domain.coinone.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CoinOneSocket (
        @JsonProperty("request_type")
        val requestType: String,

        @JsonProperty("channel")
        val channel: String,

        @JsonProperty("topic")
        val topic: CoinOneTopic
    ) {
        data class CoinOneTopic(
            @JsonProperty("quote_currency")
            val quoteCurrency: String,

            @JsonProperty("target_currency")
            val targetCurrency: String
        )

        companion object {
            fun createCoinOneSocket(coinOneTopic: CoinOneTopic): CoinOneSocket {
                return CoinOneSocket(
                    requestType = "SUBSCRIBE",
                    channel = "TICKER",
                    topic = coinOneTopic
                )
            }
    }
}
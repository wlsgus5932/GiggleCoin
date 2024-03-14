package com.giggle.domain.bithumb.response

//bithumb websocket api의 응답 값을 받기 위한 response
data class BithumbTickerResponse(
    val type: String,
    val content: TickerDto
) {
    data class TickerDto(
        val symbol: String,
        val tickType: String,
        val date: String,
        val time: String,
        val openPrice: String,
        val closePrice: String,
        val lowPrice: String,
        val highPrice: String,
        val value: String,
        val volume: String,
        val sellVolume: String,
        val buyVolume: String,
        val prevClosePrice: String,
        val chgRate: String,
        val chgAmt: String,
        val volumePower: String
    )
}
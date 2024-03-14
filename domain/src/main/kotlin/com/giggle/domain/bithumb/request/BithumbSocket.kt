package com.giggle.domain.bithumb.request

// bithumb websocket api 통신을 위한 request
data class BithumbSocket(
    val type: String,
    val symbols: List<String>,
    val tickTypes: List<String>?
) {
    companion object {
        fun createTickerRequest(symbols: List<String>): BithumbSocket {
            return BithumbSocket(
                type = "ticker",
                symbols = symbols,
                tickTypes = listOf("1H", "24")
            )
        }
    }
}
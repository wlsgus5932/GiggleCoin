package com.giggle.websocket.dto.request.coinone

//TODO: bithumb 구현 완료 시 삭제
class CoinOneParameter {
    enum class RequestType(val value: String) {
        SUBSCRIBE("SUBSCRIBE")
    }

    enum class Channel(val value: String) {
        TICKER("TICKER")
    }

    enum class QuoteCurrency(val value: String) {
        KRW("KRW")
    }

    enum class TargetCurrency(val value: String) {
        BTC("BTC"),
        XRP("XRP"),
        ETH("ETH")
    }
}
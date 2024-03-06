package com.giggle.websocket.dto.request.coinone

/*
* 코인원 웹소켓 api 파라미터들의 값 정의
* 자세한 스펙은 코인원 웹소켓 api 문서 참조
*
* */
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
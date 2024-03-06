package com.giggle.websocket.dto.request.bithumb

/*
* 빗썸 웹소켓 api 파라미터들의 값 정의
* 자세한 스펙은 빗썸 웹소켓 api 문서 참조
*
* */
class BithumbParameter {
    enum class Type(val value: String) {
        TICKER("ticker"),
        TRANSACTION("transaction"),
        ORDERBOOK("orderbook")
    }

    enum class Symbols(val value: String) {
        BTC_KRW("BTC_KRW"),
        ETH_KRW("ETH_KRW")
    }

    enum class TickTypes(val value: String) {
        THIRTY_MINUTES("30M"),
        ONE_HOUR("1H"),
        TWELVE_HOURS("12H"),
        TWENTY_FOUR_HOURS("24H"),
        MID("MID")
    }
}


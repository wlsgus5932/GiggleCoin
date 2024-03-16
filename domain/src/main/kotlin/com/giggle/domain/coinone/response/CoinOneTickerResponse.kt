package com.giggle.domain.coinone.response

data class CoinOneTickerResponse(
    val responseType: String,              // DATA로 고정
    val channel: String,                   // TICKER 고정
    val data: CoinOneTickerDto             // 구독 data 정보
) {
    data class CoinOneTickerDto(
        val quoteCurrency: String,             // 마켓 기준 통화 (예: KRW)
        val targetCurrency: String,            // 조회한 종목의 심볼 (예: BTC)
        val timestamp: Long,                   // 타임스탬프 (millisecond)
        val quoteVolume: String,               // 최근 24시간 기준 종목 체결 금액 (원화)
        val targetVolume: String,              // 최근 24시간 기준 종목 체결량 (종목)
        val high: String,                      // 고가 (UTC 기준)
        val low: String,                       // 저가 (UTC 기준)
        val first: String,                     // 시가 (UTC 기준)
        val last: String,                      // 종가 (UTC 기준)
        val volumePower: String,               // 24시간 체결 강도 (0% ~ 500%)
        val askBestPrice: String,              // 매도 오더북상 제일 낮은 호가 , 존재하지 않으면 null
        val askBestQty: String,                // 매도 오더북상 제일 낮은 호가의 수량
        val bidBestPrice: String,              // 매수 오더북상 제일 높은 호가 , 존재하지 않으면 null
        val bidBestQty: String,                // 매수 오더북상 제일 높은 호가의 수량
        val id: String,                        // 티커 별 ID 값으로 클수록 최신 티커 정보
        val yesterdayHigh: String,             // 전일 고가 (UTC 기준)
        val yesterdayLow: String,              // 전일 저가 (UTC 기준)
        val yesterdayFirst: String,            // 전일 시가 (UTC 기준)
        val yesterdayLast: String,             // 전일 종가 (UTC 기준)
        val yesterdayQuoteVolume: String,      // 전일 24시간 기준 종목 체결 금액 (원화)
        val yesterdayTargetVolume: String      // 전일 24시간 기준 종목 체결량 (종목)
    )
}

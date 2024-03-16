package com.giggle.jpa.bithumb.entity

import com.giggle.domain.bithumb.response.BithumbTickerResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "bithumb_ticker")
class BithumbTicker(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var symbol: String,
    var tickType: String,
    var date: String,
    var time: String,
    var openPrice: String,
    var closePrice: String,
    var lowPrice: String,
    var highPrice: String,
    var value: String,
    var volume: String,
    var sellVolume: String,
    var buyVolume: String,
    var prevClosePrice: String,
    var chgRate: String,
    var chgAmt: String,
    var volumePower: String
) {

    companion object {
        fun of(bithumbTicker: BithumbTicker): BithumbTickerResponse.BithumbTickerDto {
            return BithumbTickerResponse.BithumbTickerDto(
                symbol = bithumbTicker.symbol,
                tickType = bithumbTicker.tickType,
                date = bithumbTicker.date,
                time = bithumbTicker.time,
                openPrice = bithumbTicker.openPrice,
                closePrice = bithumbTicker.closePrice,
                lowPrice = bithumbTicker.lowPrice,
                highPrice = bithumbTicker.highPrice,
                value = bithumbTicker.value,
                volume = bithumbTicker.volume,
                sellVolume = bithumbTicker.sellVolume,
                buyVolume = bithumbTicker.buyVolume,
                prevClosePrice = bithumbTicker.prevClosePrice,
                chgRate = bithumbTicker.chgRate,
                chgAmt = bithumbTicker.chgAmt,
                volumePower = bithumbTicker.volumePower,
            )
        }

        fun toEntity(bithumbTickerDto: BithumbTickerResponse.BithumbTickerDto): BithumbTicker {
            return BithumbTicker(
                symbol = bithumbTickerDto.symbol,
                tickType = bithumbTickerDto.tickType,
                date = bithumbTickerDto.date,
                time = bithumbTickerDto.time,
                openPrice = bithumbTickerDto.openPrice,
                closePrice = bithumbTickerDto.closePrice,
                lowPrice = bithumbTickerDto.lowPrice,
                highPrice = bithumbTickerDto.highPrice,
                value = bithumbTickerDto.value,
                volume = bithumbTickerDto.volume,
                sellVolume = bithumbTickerDto.sellVolume,
                buyVolume = bithumbTickerDto.buyVolume,
                prevClosePrice = bithumbTickerDto.prevClosePrice,
                chgRate = bithumbTickerDto.chgRate,
                chgAmt = bithumbTickerDto.chgAmt,
                volumePower = bithumbTickerDto.volumePower,
            )
        }
    }
}
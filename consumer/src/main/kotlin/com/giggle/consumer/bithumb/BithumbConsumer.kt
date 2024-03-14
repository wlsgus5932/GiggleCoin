package com.giggle.consumer.bithumb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.domain.bithumb.response.BithumbTickerResponse
import com.giggle.jpa.bithumb.entity.BithumbTicker
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BithumbConsumer(
    private val bithumbService: BithumbService
) {

    @KafkaListener(topics = ["bithumb"], groupId = "bithumb-group")
    fun listen(message: String) {
        val result: BithumbTickerResponse = jacksonObjectMapper().readValue(message)

        val testEntity = BithumbTicker(
            id = 0,
            symbol = result.content.symbol,
            tickType = result.content.tickType,
            date = result.content.date,
            time = result.content.time,
            openPrice = result.content.openPrice,
            closePrice = result.content.closePrice,
            lowPrice = result.content.lowPrice,
            highPrice = result.content.highPrice,
            value = result.content.value,
            volume = result.content.volume,
            sellVolume = result.content.sellVolume,
            buyVolume = result.content.buyVolume,
            prevClosePrice = result.content.prevClosePrice,
            chgRate = result.content.chgRate,
            chgAmt = result.content.chgAmt,
            volumePower = result.content.volumePower
        )

        bithumbService.saveTicker(testEntity)
        println("recieved: $result")
    }

}
package com.giggle.consumer.bithumb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.giggle.domain.bithumb.response.BithumbTickerResponse
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BithumbService(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    @Transactional
    fun saveTicker(tickerDto: BithumbTickerResponse.BithumbTickerDto) {
        val startTime = System.currentTimeMillis()

        val tickerJson = jacksonObjectMapper().writeValueAsString(tickerDto)

        stringRedisTemplate.opsForValue().set("ticker:${tickerDto.symbol}:${tickerDto.time}", tickerJson)

        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime

        println("Data saved to Redis Elapsed Time: $elapsedTime ms")
    }

    //TODO: 10분 내 코인 상승률 계산을 실시간으로 처리
    @Transactional(readOnly = true)
    fun calculatePriceIncreaseLast10Minutes(symbol: String): Double {
        val tenMinutesAgo = LocalDateTime.now().minusMinutes(10)
        val keys = stringRedisTemplate.keys("ticker:$symbol:*")

        val prices = keys
            .mapNotNull { key -> stringRedisTemplate.opsForValue().get(key) }
            .map { json -> jacksonObjectMapper().readValue(json, BithumbTickerResponse.BithumbTickerDto::class.java) }
            .mapNotNull { tickerDto ->
                val dateTime = LocalDateTime.parse("${tickerDto.date}${tickerDto.time}", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                tickerDto to dateTime
            }
            .filter { (_, dateTime) -> dateTime.isAfter(tenMinutesAgo) || dateTime.isEqual(tenMinutesAgo) }
            .sortedBy { it.second }
            .mapNotNull { it.first.closePrice.toDoubleOrNull() }

        println("price::: ${prices.first()} ${prices.last()}")

        return if (prices.size > 1) {
            ((prices.last() - prices.first()) / prices.first()) * 100
        } else {
            0.0 // 가격 정보가 없는 경우에는 0.0을 반환
        }
    }
}
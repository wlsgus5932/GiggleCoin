package com.giggle.websocket.publisher

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class RedisPublisher (
    private val messagingTemplate: SimpMessagingTemplate,
    private val redisTemplate: StringRedisTemplate
) {

    companion object {
        private const val TOPIC = "/topic/bithumb"
        private const val DATA_KEY = "ticker:BTC_KRW"
    }

    @Scheduled(fixedRate = 500) // 일정한 간격으로 Redis에서 데이터를 읽어와 클라이언트로 전송
    fun publishMessage() {
//        val data = redisTemplate.opsForValue().get(DATA_KEY)
        val keys = redisTemplate.keys("ticker:BTC_KRW:*")

        val latestKey = keys.maxByOrNull { key ->
            redisTemplate.opsForZSet().score("sortedSetKey", key)?.toDouble() ?: 0.0
        }

        val testData: String = latestKey.let { redisTemplate.opsForValue().get(it!!) }!!

        messagingTemplate.convertAndSend(TOPIC, testData) // 클라이언트에게 데이터를 발행

    }
}
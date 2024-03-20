package com.giggle.websocket.service

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
        private const val DATA_KEY = "ticker:BTC_KRW:*"
    }

    @Scheduled(fixedRate = 500)
    fun publishMessage() {
        val keys = redisTemplate.keys(DATA_KEY)

        val latestKey = keys.maxByOrNull { key ->
            redisTemplate.opsForZSet().score("sortedSetKey", key)?.toDouble() ?: 0.0
        }

        val testData: String = latestKey.let { redisTemplate.opsForValue().get(it!!) }!!

        messagingTemplate.convertAndSend(TOPIC, testData)
    }
}
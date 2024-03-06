package com.giggle.websocket.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

//TODO: Redis module로 분리하기
@Service
class RedisPublishService(
    private val redisTemplate: RedisTemplate<String, String>,
) {
    fun publish(topic: String, message: Any) {
        val messageAsString = jacksonObjectMapper().writeValueAsString(message)
        redisTemplate.convertAndSend(topic, messageAsString)
    }
}
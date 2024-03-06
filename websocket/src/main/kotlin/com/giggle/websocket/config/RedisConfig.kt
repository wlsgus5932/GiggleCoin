package com.giggle.websocket.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter

//TODO: Redis module 분리하기
@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(connectionFactory)
        }
    }

    // Redis 메시지 리스너 구성
    @Bean
    fun messageListenerAdapter(): MessageListenerAdapter {
        return MessageListenerAdapter(TestListener())
    }

    @Bean
    fun redisContainer(connectionFactory: RedisConnectionFactory): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(messageListenerAdapter(), topic())
        return container
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("test")
    }

    class TestListener : MessageListener {
        override fun onMessage(message: Message, pattern: ByteArray?) {
            println(message)
        }
    }
}


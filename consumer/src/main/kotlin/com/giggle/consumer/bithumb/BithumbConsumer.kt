package com.giggle.consumer.bithumb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.domain.bithumb.response.BithumbTickerResponse
import com.giggle.jpa.bithumb.entity.BithumbTicker
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BithumbConsumer(
    private val bithumbService: BithumbService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["bithumb"], groupId = "bithumb-group")
    fun listen(message: String) {
        val response: BithumbTickerResponse = jacksonObjectMapper().readValue(message)

        bithumbService.saveTicker(
            BithumbTicker.toEntity(response.content)
        )

        logger.info("recieved: $response")
    }

}
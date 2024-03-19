package com.giggle.consumer.bithumb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.giggle.domain.bithumb.response.BithumbTickerResponse
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BithumbConsumer(
    private val bithumbService: BithumbService
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["bithumb"], groupId = "bithumb-group")
    fun listen(record: ConsumerRecord<String, String>, consumer: Consumer<*, *>) {
        val response: BithumbTickerResponse = jacksonObjectMapper().readValue(record.value())

        // Consumer Lag 조회
        //TODO: kafka 모니터링 구축
        val partition = record.partition()
        val offset = record.offset()
        val topicPartition = org.apache.kafka.common.TopicPartition(record.topic(), partition)
        val endOffsets = consumer.endOffsets(listOf(topicPartition)).getOrDefault(topicPartition, 0)
        val lag = endOffsets - offset
        logger.info("Consumer Lag for partition $partition: $lag")

        bithumbService.saveTicker(response.content)
        logger.info("Received: ${response.content.closePrice}")
    }

}
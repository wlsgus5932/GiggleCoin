package com.giggle.consumer.bithumb

import com.giggle.domain.bithumb.response.BithumbTickerResponse
import com.giggle.jpa.bithumb.repository.TickerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BithumbService(
    private val tickerRepository: TickerRepository
) {

    fun saveTicker(tickerResponse: BithumbTickerResponse) {
        tickerRepository.save()
    }



}
package com.giggle.consumer.bithumb

import com.giggle.jpa.bithumb.entity.BithumbTicker
import com.giggle.jpa.bithumb.repository.BithumbTickerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BithumbService(
    private val bithumbTickerRepository: BithumbTickerRepository
) {

    fun saveTicker(bithumbTicker: BithumbTicker) {
        bithumbTickerRepository.save(bithumbTicker)
    }
}
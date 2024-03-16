package com.giggle.jpa.bithumb.repository

import com.giggle.jpa.bithumb.entity.BithumbTicker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BithumbTickerRepository: JpaRepository<BithumbTicker, Long>
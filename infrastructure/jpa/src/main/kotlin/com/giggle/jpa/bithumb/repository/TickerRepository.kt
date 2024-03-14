package com.giggle.jpa.bithumb.repository

import com.giggle.jpa.bithumb.entity.BithumbTicker
import org.springframework.data.jpa.repository.JpaRepository

interface TickerRepository: JpaRepository<BithumbTicker, Long>
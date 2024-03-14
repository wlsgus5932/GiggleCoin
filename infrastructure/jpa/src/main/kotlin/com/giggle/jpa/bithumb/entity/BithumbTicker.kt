package com.giggle.jpa.bithumb.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "bithumb_ticker")
class BithumbTicker(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var symbol: String,
    var tickType: String,
    var date: String,
    var time: String,
    var openPrice: String,
    var closePrice: String,
    var lowPrice: String,
    var highPrice: String,
    var value: String,
    var volume: String,
    var sellVolume: String,
    var buyVolume: String,
    var prevClosePrice: String,
    var chgRate: String,
    var chgAmt: String,
    var volumePower: String
)
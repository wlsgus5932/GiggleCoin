package com.giggle.com.giggle.websocket.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/*
* Logger 확장함수
* */
fun Any.Logger(): Logger = LoggerFactory.getLogger(this::class.java)
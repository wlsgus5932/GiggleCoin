package com.giggle.webclient.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/*
* Logger 확장함수
* */
fun Any.Logger(): Logger = LoggerFactory.getLogger(this::class.java)
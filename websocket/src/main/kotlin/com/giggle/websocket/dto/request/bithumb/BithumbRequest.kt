package com.giggle.websocket.dto.request.bithumb

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class BithumbRequest(
    val type: String,
    val symbols: List<String>,
    val tickTypes: List<String>?
) {
    companion object {
        fun of(type: String, symbols: List<String>, tickTypes: List<String>?): String {
            return jacksonObjectMapper()
                .writeValueAsString(
                    BithumbRequest(type, symbols, tickTypes)
                )
        }
    }
}
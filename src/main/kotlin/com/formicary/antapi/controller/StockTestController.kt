package com.formicary.antapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yahoofinance.Stock
import yahoofinance.YahooFinance

@RestController
@RequestMapping("/api/v1/stock")
class StockTestController() {

    @GetMapping("/test")
    fun getStock(): Stock? {
        val stock = YahooFinance.get("INTC")
        return stock
    }
}

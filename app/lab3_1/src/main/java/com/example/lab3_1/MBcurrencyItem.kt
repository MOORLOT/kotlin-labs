package com.example.lab3_1

data class MBcurrencyItem(
    val currencyCodeA: Int,
    val currencyCodeB: Int,
    val date: Int,
    val rateBuy: Double,
    val rateCross: Double,
    val rateSell: Double
)
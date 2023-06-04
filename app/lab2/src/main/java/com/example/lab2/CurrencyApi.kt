package com.example.lab2

import retrofit2.http.GET

interface CurrencyApi {
    @GET("bank/currency")
    suspend fun getCurrencies(): List<Currency>
}


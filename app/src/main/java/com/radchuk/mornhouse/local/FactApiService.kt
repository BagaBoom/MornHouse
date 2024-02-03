package com.radchuk.mornhouse.local

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object FactApiService {
    private const val BASE_URL = "http://numbersapi.com/"

    val apiClient: FactApiClient = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(FactApiClient::class.java)
}

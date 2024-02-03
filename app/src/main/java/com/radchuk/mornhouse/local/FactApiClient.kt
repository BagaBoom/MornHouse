package com.radchuk.mornhouse.local

import retrofit2.http.GET
import retrofit2.http.Path

interface FactApiClient {
    @GET("{number}")
    suspend fun getFactByNumber(@Path("number") number: Int): String

    @GET("random/math")
    suspend fun getRandomFact(): String
}

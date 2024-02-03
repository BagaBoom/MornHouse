package com.radchuk.mornhouse.local

class FactRepositoryRemote(private val apiClient: FactApiClient) {
    suspend fun getFactByNumber(number: Int): String {
        return apiClient.getFactByNumber(number)
    }

    suspend fun getRandomFact(): String {
        return apiClient.getRandomFact()
    }
}

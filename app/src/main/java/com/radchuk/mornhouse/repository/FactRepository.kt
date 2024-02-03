package com.radchuk.mornhouse.repository

import com.radchuk.mornhouse.data.dao.FactDao
import com.radchuk.mornhouse.data.model.Fact

class FactRepository(private val factDao: FactDao) {
    suspend fun insertFact(fact: Fact) {
        factDao.insertFact(fact)
    }

    suspend fun getAllFacts(): List<Fact> {
        return factDao.getAllFacts()
    }
}

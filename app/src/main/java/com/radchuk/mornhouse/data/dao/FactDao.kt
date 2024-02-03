package com.radchuk.mornhouse.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.radchuk.mornhouse.data.model.Fact

@Dao
interface FactDao {
    @Insert
    suspend fun insertFact(fact: Fact)

    @Query("SELECT * FROM facts ORDER BY id DESC")
    suspend fun getAllFacts(): List<Fact>
}

package com.radchuk.mornhouse.ui.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radchuk.mornhouse.data.model.Fact
import com.radchuk.mornhouse.repository.FactRepository
import kotlinx.coroutines.launch

class FactViewModel(private val repository: FactRepository) : ViewModel() {
    fun insertFact(fact: Fact) {
        viewModelScope.launch {
            repository.insertFact(fact)
        }
    }

    suspend fun getAllFacts() = repository.getAllFacts()
}

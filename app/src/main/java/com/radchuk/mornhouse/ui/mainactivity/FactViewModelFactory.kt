package com.radchuk.mornhouse.ui.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.radchuk.mornhouse.repository.FactRepository

class FactViewModelFactory(private val repository: FactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FactRepository::class.java).newInstance(repository)
    }
}


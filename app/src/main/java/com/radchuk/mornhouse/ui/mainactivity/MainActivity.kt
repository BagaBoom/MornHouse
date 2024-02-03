package com.radchuk.mornhouse.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.radchuk.mornhouse.R
import com.radchuk.mornhouse.local.FactApiService
import com.radchuk.mornhouse.local.FactRepositoryRemote
import com.radchuk.mornhouse.databinding.ActivityMainBinding
import com.radchuk.mornhouse.data.db.AppDatabase
import com.radchuk.mornhouse.data.model.Fact
import com.radchuk.mornhouse.repository.FactRepository
import com.radchuk.mornhouse.ui.adapter.FactHistoryAdapter
import com.radchuk.mornhouse.ui.detailactivity.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: FactViewModel
    private lateinit var historyAdapter: FactHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FactRepository(
            AppDatabase.getInstance(application).factDao()
        )
        viewModel = ViewModelProvider(this, FactViewModelFactory(repository)).get(FactViewModel::class.java)

        historyAdapter = FactHistoryAdapter(emptyList()) {
            navigateToDetailScreen(it)
        }
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter

        binding.getFactButton.setOnClickListener {
            val number = binding.numberEditText.text.toString().toIntOrNull()
            if (number != null) {
                getFact(number)
            }else{
                Toast.makeText(this, getString(R.string.enter_a_number), Toast.LENGTH_SHORT).show()
            }
        }

        binding.getRandomFactButton.setOnClickListener {
            getRandomFact()
        }

        loadHistory()
    }

    private fun getFact(number: Int) {
        MainScope().launch(Dispatchers.Main) {
            val factText = withContext(Dispatchers.IO) {
                FactRepositoryRemote(FactApiService.apiClient).getFactByNumber(number)
            }
            navigateToDetailScreen(Fact(number = number, text = factText))
            viewModel.insertFact(Fact(number = number, text = factText))
            updateHistory()
        }
    }

    private fun getRandomFact() {
        MainScope().launch(Dispatchers.Main) {
            val factText = withContext(Dispatchers.IO) {
                FactRepositoryRemote(FactApiService.apiClient).getRandomFact()
            }
            val number = factText.split(" ").get(0).toInt()
            navigateToDetailScreen(Fact(number = number, text = factText))
            viewModel.insertFact(Fact(number = number, text = factText))
            updateHistory()

        }
    }

    private fun loadHistory() {
        MainScope().launch(Dispatchers.Main) {
            val history = withContext(Dispatchers.IO) {
                viewModel.getAllFacts()
            }
            historyAdapter = FactHistoryAdapter(history) {
                navigateToDetailScreen(it)
            }
            binding.historyRecyclerView.adapter = historyAdapter
        }
    }

    private fun updateHistory() {
        MainScope().launch(Dispatchers.Main) {
            val history = withContext(Dispatchers.IO) {
                viewModel.getAllFacts()
            }
            historyAdapter.updateData(history)
        }
    }

    private fun navigateToDetailScreen(fact: Fact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FACT, fact)
        startActivity(intent)
    }
}

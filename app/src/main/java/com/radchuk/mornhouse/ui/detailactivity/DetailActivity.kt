package com.radchuk.mornhouse.ui.detailactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radchuk.mornhouse.databinding.ActivityDetailBinding
import com.radchuk.mornhouse.data.model.Fact

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_FACT = "extra_fact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fact = intent.getParcelableExtra<Fact>(EXTRA_FACT)
        fact?.let {
            binding.detailNumberTextView.text = it.number.toString()
            binding.detailFactTextView.text = it.text
        }

        binding.imageButtonBack.setOnClickListener {
            this.finish()
        }
    }
}

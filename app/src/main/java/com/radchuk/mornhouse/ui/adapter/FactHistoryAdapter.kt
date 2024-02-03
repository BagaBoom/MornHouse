package com.radchuk.mornhouse.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.radchuk.mornhouse.R
import com.radchuk.mornhouse.data.model.Fact

class FactHistoryAdapter(
    private var history: List<Fact>,
    private val onItemClick: (Fact) -> Unit
) : RecyclerView.Adapter<FactHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fact_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = history[position]
        holder.bind(fact)
        holder.itemView.setOnClickListener { onItemClick(fact) }
    }

    override fun getItemCount(): Int = history.size

    fun updateData(newHistory: List<Fact>) {
        history = newHistory
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fact: Fact) {
            itemView.findViewById<TextView>(R.id.historyNumberTextView).text = fact.number.toString()
            itemView.findViewById<TextView>(R.id.historyPreviewTextView).text = fact.text
        }
    }

}


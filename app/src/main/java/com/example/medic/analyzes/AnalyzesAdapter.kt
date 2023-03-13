package com.example.medic.analyzes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medic.databinding.AnalyzesItemBinding
import com.example.medic.model.Analyses

class AnalyzesAdapter: RecyclerView.Adapter<AnalyzesAdapter.AnalyzesViewHolder>() {
    class AnalyzesViewHolder(private val binding: AnalyzesItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(analyses: Analyses) {
            binding.priceTV.text = analyses.price
            binding.timeResultTV.text = analyses.timeResult
            binding.titleTV.text = analyses.name
        }
    }

    private val items = mutableListOf<Analyses>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyzesViewHolder {
        return AnalyzesViewHolder(AnalyzesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AnalyzesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitItems(items: List<Analyses>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}



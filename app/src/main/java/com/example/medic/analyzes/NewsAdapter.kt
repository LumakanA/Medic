package com.example.medic.analyzes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medic.databinding.NewsItemBinding
import com.example.medic.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(news: News){
            binding.nameTV.text = news.name
            binding.descriptionTV.text = news.description
            binding.priceTV.text = news.price
            Glide.with(binding.imageIV.context)
                .load(news.image)
                .into(binding.imageIV)
        }
    }

    private val items = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }
    @SuppressLint("notifyDataSetChanged")
    fun submitItems(items: List<News>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
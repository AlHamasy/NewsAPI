package com.asad.newsapi.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asad.newsapi.R
import com.asad.newsapi.data.network.response.ArticlesItem
import com.asad.newsapi.databinding.ItemNewsBinding
import com.asad.newsapi.ui.detail.DetailNewsActivity
import com.bumptech.glide.Glide

class NewsAdapter(val onItemClick : (ArticlesItem) -> Unit)
    : PagingDataAdapter<ArticlesItem, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>(){
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemRowNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemRowNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articlesItem = getItem(position)
        if (articlesItem != null){
            holder.bind(articlesItem)
        }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(articlesItem: ArticlesItem){
            with(binding){
                tvRowSource.text = articlesItem.source?.name
                tvRowTitle.text = articlesItem.title
                tvRowDescription.text = articlesItem.description
                Glide.with(binding.root)
                    .load(articlesItem.urlToImage)
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_error)
                    .into(binding.imgRowNews)
            }
            itemView.setOnClickListener {
                onItemClick(articlesItem)
            }
        }
    }
}
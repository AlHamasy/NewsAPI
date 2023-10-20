package com.asad.newsapi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asad.newsapi.data.network.response.ArticlesItem
import com.asad.newsapi.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val detailData =  intent.getParcelableExtra<ArticlesItem>(ARTICLE_ITEM)
        Toast.makeText(this, detailData?.title, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val ARTICLE_ITEM = "article_item"
    }



}
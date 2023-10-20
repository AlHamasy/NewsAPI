package com.asad.newsapi.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.newsapi.databinding.ActivityMainBinding
import com.asad.newsapi.ui.detail.DetailNewsActivity
import com.asad.newsapi.viewmodel.ViewModelFactory
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showNewsArticle()
    }

    private fun showNewsArticle(){

        binding.rvNews.visibility = View.VISIBLE

        val newsAdapter = NewsAdapter{articlesItem ->
            val intent = Intent(this@MainActivity, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.ARTICLE_ITEM, articlesItem)
            startActivity(intent)
        }

        mainViewModel.newsArticles.observe(this) { data ->
            newsAdapter.submitData(lifecycle, data)
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }

        newsAdapter.addLoadStateListener {loadState ->
            if (newsAdapter.itemCount >= 1){
                binding.loadingBar.visibility = View.GONE
            }
        }
    }

}
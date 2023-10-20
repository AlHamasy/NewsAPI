package com.asad.newsapi.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.newsapi.data.network.response.ArticlesItem
import com.asad.newsapi.databinding.ActivityMainBinding
import com.asad.newsapi.ui.detail.DetailNewsActivity
import com.asad.newsapi.utils.Resource
import com.asad.newsapi.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest

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

    private fun showError(){
        binding.tvError.visibility = View.VISIBLE
        binding.tvLoading.visibility = View.GONE
        binding.rvNews.visibility = View.GONE
    }

    private fun showNewsArticle(){
        binding.rvNews.visibility = View.VISIBLE

        val newsAdapter = NewsAdapter{articlesItem ->
            val intent = Intent(this@MainActivity, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.ARTICLE_ITEM, articlesItem)
            startActivity(intent)
        }

        newsAdapter.addLoadStateListener {loadState ->
            if (newsAdapter.itemCount >= 1){
                binding.tvLoading.visibility = View.GONE
            }
            else {
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    showError()
                }
            }
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
        mainViewModel.newsArticles.observe(this) { data ->
            newsAdapter.submitData(lifecycle, data)
        }
    }

}
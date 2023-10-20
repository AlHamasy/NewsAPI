package com.asad.newsapi.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.asad.newsapi.data.NewsPagingSource
import com.asad.newsapi.data.network.response.ArticlesItem
import com.asad.newsapi.utils.Resource

class MainViewModel: ViewModel() {

    val newsArticles: LiveData<PagingData<ArticlesItem>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            NewsPagingSource()
        }
    ).liveData.cachedIn(viewModelScope)

}
package com.asad.newsapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asad.newsapi.BuildConfig
import com.asad.newsapi.data.network.ApiConfig
import com.asad.newsapi.data.network.response.ArticlesItem

class NewsPagingSource() : PagingSource<Int, ArticlesItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseMovie = ApiConfig.getApiService().getNewsArticles(BuildConfig.API_KEY, page = page).articles
            LoadResult.Page(
                data = responseMovie!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseMovie.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
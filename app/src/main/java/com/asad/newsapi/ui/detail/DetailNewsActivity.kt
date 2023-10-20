package com.asad.newsapi.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.asad.newsapi.R
import com.asad.newsapi.data.network.response.ArticlesItem
import com.asad.newsapi.databinding.ActivityDetailNewsBinding
import com.asad.newsapi.utils.GlobalFunction
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
        showDetailArticle()
    }

    private fun initActionBar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_news_detail)
    }

    private fun showDetailArticle(){
        val detailData =  intent.getParcelableExtra<ArticlesItem>(ARTICLE_ITEM)
        binding.apply {
            tvAuthorDetail.text = getString(R.string.author_and_source, detailData?.author, detailData?.source?.name)
            tvDescriptionDetail.text = detailData?.description
            tvTitleDetail.text = detailData?.title
            tvPublishedAtDetail.text = GlobalFunction.convertDate(detailData?.publishedAt ?: "", "dd MMMM yyyy - HH:mm")
            Glide.with(this@DetailNewsActivity)
                .load(detailData?.urlToImage)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error)
                .into(imgDetail)

            tvOpenWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(detailData?.url)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val ARTICLE_ITEM = "article_item"
    }

}
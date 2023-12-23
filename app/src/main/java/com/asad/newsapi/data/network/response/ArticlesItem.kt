package com.asad.newsapi.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String?,

	@field:SerializedName("author")
	val author: String?,

	@field:SerializedName("urlToImage")
	val urlToImage: String?,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("source")
	val source: Source?,

	@field:SerializedName("title")
	val title: String?,

	@field:SerializedName("url")
	val url: String?,

	@field:SerializedName("content")
	val content: String?

) : Parcelable
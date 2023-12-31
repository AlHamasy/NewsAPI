package com.asad.newsapi.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@kotlinx.parcelize.Parcelize
data class Source(

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("id")
	val id: String?

) : Parcelable
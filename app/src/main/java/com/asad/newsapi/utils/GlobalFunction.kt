package com.asad.newsapi.utils

import java.text.SimpleDateFormat
import java.util.Locale

class GlobalFunction {
    companion object{
        fun convertDate(inputDate : String, format : String = "dd MMM yyyy - HH:mm") : String{
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat(format, Locale.getDefault())
            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        }
    }
}
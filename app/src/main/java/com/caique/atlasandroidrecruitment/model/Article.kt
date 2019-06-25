package com.caique.atlasandroidrecruitment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Article (@SerializedName("source") val source: SourceResponse,
               @SerializedName("author") val author: String,
               @SerializedName("title") val title: String,
               @SerializedName("description") val description: String,
               @SerializedName("url") val url: String,
               @SerializedName("urlToImage") val urlToImage: String,
               @SerializedName("publishedAt") var publishedAt: String,
               @SerializedName("content") val content: String) : Serializable
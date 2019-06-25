package com.caique.atlasandroidrecruitment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewsResponse (@SerializedName("status") val status : String,
                    @SerializedName("totalResults") val totalResults: Int,
                    @SerializedName("articles") val articles : ArrayList<Article>) : Serializable
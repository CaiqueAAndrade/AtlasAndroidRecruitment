package com.caique.atlasandroidrecruitment.data.remote

import com.caique.atlasandroidrecruitment.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {

    @GET("everything?q=bitcoin&from=2019-05-24&sortBy=publishedAt&apiKey=0abcbac8aca94184a29ac065406c27da")
    fun getNews() : Call<NewsResponse>
}
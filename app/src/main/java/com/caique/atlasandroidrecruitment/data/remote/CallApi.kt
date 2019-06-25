package com.caique.atlasandroidrecruitment.data.remote

import com.caique.atlasandroidrecruitment.model.NewsResponse
import com.caique.atlasandroidrecruitment.utils.Constants.Companion.API_PARAM
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {

    @GET(API_PARAM)
    fun getNews() : Call<NewsResponse>
}
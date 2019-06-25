package com.caique.atlasandroidrecruitment.repository

import android.arch.lifecycle.MutableLiveData
import com.caique.atlasandroidrecruitment.data.remote.CallApi
import com.caique.atlasandroidrecruitment.data.remote.RetrofitClientInstance
import com.caique.atlasandroidrecruitment.model.NewsResponse
import retrofit2.Call
import retrofit2.Response

class NewsRepository  {

    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
    val error: MutableLiveData<String> = MutableLiveData()

    fun getNews() : MutableLiveData<NewsResponse> {
        val newsResponseMutableLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
        service.getNews().enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                newsResponseMutableLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                newsResponseMutableLiveData.postValue(null)
                error.postValue(t.message)
            }
        })
        return newsResponseMutableLiveData
    }

    object NewsRepositoryProvider {
        fun provideNewsRepository() : NewsRepository {
            return NewsRepository()
        }
    }
}
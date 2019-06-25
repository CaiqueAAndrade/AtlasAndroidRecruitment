package com.caique.atlasandroidrecruitment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.caique.atlasandroidrecruitment.model.NewsResponse
import com.caique.atlasandroidrecruitment.repository.NewsRepository

class NewsViewModel (application: Application) : AndroidViewModel(application) {

    private val newsRepository = NewsRepository.NewsRepositoryProvider.provideNewsRepository()

    fun getNews() : LiveData<NewsResponse> {
        return newsRepository.getNews()
    }

    fun handlerErro() : LiveData<String>{
        return newsRepository.error
    }
}
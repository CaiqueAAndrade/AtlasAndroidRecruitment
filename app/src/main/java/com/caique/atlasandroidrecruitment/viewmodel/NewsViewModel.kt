package com.caique.atlasandroidrecruitment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.caique.atlasandroidrecruitment.extension.formatDate
import com.caique.atlasandroidrecruitment.model.Article
import com.caique.atlasandroidrecruitment.model.NewsResponse
import com.caique.atlasandroidrecruitment.repository.NewsRepository

class NewsViewModel (application: Application) : AndroidViewModel(application) {

    private val newsRepository = NewsRepository.NewsRepositoryProvider.provideNewsRepository()
    private val newsResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    private val getArticle: MutableLiveData<Article> = MutableLiveData()
    private val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val showError: MutableLiveData<String> = MutableLiveData()

    fun getArticleObservable() : LiveData<Article> {
        return getArticle
    }

    fun showLoadingObservable() : LiveData<Boolean> {
        return showLoading
    }

    fun newsResponseObserver() : LiveData<NewsResponse> {
        return newsResponse
    }

    fun showErrorObserver() : LiveData<String> {
        return showError
    }

    fun getNews() {
        showLoading.postValue(true)
        val newsResponseObserver: LiveData<NewsResponse> = newsRepository.getNews()
        handlerError()
        newsResponseObserver.observeForever(object : Observer<NewsResponse> {
            override fun onChanged(t: NewsResponse?) {
                newsResponse.postValue(t)
                showLoading.postValue(false)
                newsResponseObserver.removeObserver(this)
            }

        })
    }

    private fun handlerError() {
        val error: MutableLiveData<String> = newsRepository.error
        error.observeForever(object : Observer<String> {
            override fun onChanged(t: String?) {
                showError.postValue(t)
                error.removeObserver(this)
            }

        })
    }

    fun setArticlesParcelable(article: Article) {
        article.publishedAt = article.publishedAt.formatDate()
        getArticle.postValue(article)
    }
}
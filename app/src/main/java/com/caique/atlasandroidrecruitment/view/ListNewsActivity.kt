package com.caique.atlasandroidrecruitment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.caique.atlasandroidrecruitment.R
import com.caique.atlasandroidrecruitment.adapter.NewsListRecyclerViewAdapter
import com.caique.atlasandroidrecruitment.model.NewsResponse
import com.caique.atlasandroidrecruitment.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_listnews.*

class ListNewsActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listnews)

        newsViewModel = ViewModelProviders.of(this)[NewsViewModel::class.java]

        setupUi()
    }

    private fun setupUi() {
        newsViewModel.getNews().observe(this, Observer {
            if (it != null) {
                loading.visibility = View.GONE
                setRecyclerView(it)
            } else {
                onFailure()
            }
        })
    }

    private fun setRecyclerView(newsResponse: NewsResponse) {
        val recyclerView = rv_news
        val newsAdapter = NewsListRecyclerViewAdapter(this, newsResponse.articles)
        recyclerView.adapter = newsAdapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        onItemClick(newsAdapter)
    }

    private fun onItemClick(newsAdapter: NewsListRecyclerViewAdapter) {
        newsAdapter.onItemClick = {
            Toast.makeText(this, it.title + "", Toast.LENGTH_LONG).show()
        }
    }

    private fun onFailure() {

    }
}
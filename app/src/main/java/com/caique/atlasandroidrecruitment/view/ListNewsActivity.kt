package com.caique.atlasandroidrecruitment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.caique.atlasandroidrecruitment.R
import com.caique.atlasandroidrecruitment.adapter.NewsListRecyclerViewAdapter
import com.caique.atlasandroidrecruitment.model.NewsResponse
import com.caique.atlasandroidrecruitment.utils.Constants.Companion.SERIALIZABLE_KEY
import com.caique.atlasandroidrecruitment.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_listnews.*

class ListNewsActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listnews)

        newsViewModel = ViewModelProviders.of(this)[NewsViewModel::class.java]

        subscribe()
        setupUi()
    }

    private fun setupUi() {
        newsViewModel.getNews()
    }

    private fun subscribe() {
        newsViewModel.showErrorObserver().observe(this, Observer {
          if (it != null) {
              onFailure(it)
          }
        })
        newsViewModel.newsResponseObserver().observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
        newsViewModel.showLoadingObservable().observe(this, Observer {
            if (it != null) {
                showLoading(it)
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
            val intent = Intent(this, NewsInfoActivity::class.java)
            intent.putExtra(SERIALIZABLE_KEY, it)
            startActivity(intent)
        }
    }

    private fun onFailure(error: String) {
        error_animation.visibility = View.VISIBLE
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(boolean: Boolean) {
        if (!boolean) {
            loading.visibility = View.GONE
        }
    }
}
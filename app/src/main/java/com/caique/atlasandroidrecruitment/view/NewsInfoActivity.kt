package com.caique.atlasandroidrecruitment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caique.atlasandroidrecruitment.R
import com.caique.atlasandroidrecruitment.databinding.ActivityNewsinfoBinding
import com.caique.atlasandroidrecruitment.model.Article
import com.caique.atlasandroidrecruitment.utils.Constants.Companion.SERIALIZABLE_KEY
import com.caique.atlasandroidrecruitment.viewmodel.NewsViewModel
import com.squareup.picasso.Picasso


class NewsInfoActivity : AppCompatActivity() {

    lateinit var newsInfoActivityBinding:  ActivityNewsinfoBinding
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsinfo)

        newsViewModel = ViewModelProviders.of(this)[NewsViewModel::class.java]

        newsViewModel.setArticlesParcelable(intent.getSerializableExtra(SERIALIZABLE_KEY) as Article)
        subscribe()
    }

    private fun subscribe() {
        newsViewModel.getArticleObservable().observe(this, Observer {
            if (it != null) {
                setupUi(it)
            }
        })
    }

    private fun setupUi(article: Article) {
        newsInfoActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_newsinfo)
        newsInfoActivityBinding.setVariable(1, article)
        newsInfoActivityBinding.executePendingBindings()
        Picasso.get().load(article.urlToImage).error(R.drawable.imagenotfound).into(newsInfoActivityBinding.image)
    }
}
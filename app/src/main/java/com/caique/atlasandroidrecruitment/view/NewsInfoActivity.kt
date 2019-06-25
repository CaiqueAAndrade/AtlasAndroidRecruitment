package com.caique.atlasandroidrecruitment.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caique.atlasandroidrecruitment.R
import com.caique.atlasandroidrecruitment.databinding.ActivityNewsinfoBinding
import com.caique.atlasandroidrecruitment.extension.formatDate
import com.caique.atlasandroidrecruitment.model.Article
import com.squareup.picasso.Picasso

class NewsInfoActivity : AppCompatActivity() {

    lateinit var newsInfoActivityBinding:  ActivityNewsinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsinfo)

        val article: Article = intent.getSerializableExtra("article") as Article
        setupUi(article)
    }

    private fun setupUi(article: Article) {
        article.publishedAt = article.publishedAt.formatDate()
        newsInfoActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_newsinfo)
        newsInfoActivityBinding.setVariable(1, article)
        newsInfoActivityBinding.executePendingBindings()
        Picasso.get().load(article.urlToImage).error(R.drawable.imagenotfound).into(newsInfoActivityBinding.image)
    }
}
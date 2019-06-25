package com.caique.atlasandroidrecruitment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caique.atlasandroidrecruitment.R
import com.caique.atlasandroidrecruitment.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListRecyclerViewAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsListRecyclerViewAdapter.ViewHolder>() {

    var onItemClick: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val article = articles[p1]

        p0.title.text = article.title
        p0.description.text = article.description
        p0.date.text = article.publishedAt
        p0.source.text = article.source.name

        Picasso.get().load(article.urlToImage).error(R.drawable.imagenotfound).into(p0.image)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image
        val title = itemView.tv_title
        val description = itemView.tv_description
        val date = itemView.tv_date
        val source = itemView.tv_source

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(articles[adapterPosition])
            }
        }
    }
}
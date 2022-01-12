package com.jagad.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jagad.evaluation.R
import com.jagad.evaluation.data.ArticleListData
import com.jagad.evaluation.databinding.ArticleListItemBinding

/**
 * Created by jagad on 1/12/2022
 */

class ArticleListAdapter():RecyclerView.Adapter<ArticleListAdapter.articlesViewHolder>() {

    var articleList = listOf<ArticleListData>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = articlesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.article_list_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: articlesViewHolder, position: Int) {
        holder.articleListItemBinding.articles = articleList[position]
    }

    override fun getItemCount() = articleList.size

    inner class articlesViewHolder(
        val articleListItemBinding: ArticleListItemBinding
    ):RecyclerView.ViewHolder(articleListItemBinding.root)
}
package com.jagad.evaluation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.jagad.evaluation.R
import com.jagad.evaluation.adapter.ArticleListAdapter
import com.jagad.evaluation.databinding.FragmentArticleListBinding
import com.jagad.evaluation.network.NetworkResult
import com.jagad.evaluation.util.*
import com.jagad.evaluation.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by jagad on 1/11/2022
 */

@AndroidEntryPoint
class ArticleListFragment : Fragment(R.layout.fragment_article_list) {

    private lateinit var articlesBinding:FragmentArticleListBinding
    private val articleViewModel:ArticlesViewModel by viewModels()

    private lateinit var articlesAdapter: ArticleListAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesBinding = FragmentArticleListBinding.bind(view)
        initialize()
    }

    private fun initialize(){
        articlesBinding.articleviewmodel = articleViewModel
        articlesAdapter = ArticleListAdapter()
        articlesBinding.adapter = articlesAdapter

        hideKeyBoard()
        navigate()
        viewModelObserver()
    }

    private fun navigate(){
        when(val navigateFrom = arguments?.getString(Passparams.NAVIGATE_FROM,"")){
            Passparams.SEARCH -> articleViewModel.getArticles(arguments?.getString(Passparams.SEARCH_TXT,"")!!)
            else -> articleViewModel.getMostPopularAPI(navigateFrom!!)
        }
    }

    private fun viewModelObserver(){
        articleViewModel.articleList.observe(viewLifecycleOwner,{
            articlesAdapter.articleList = it
        })

        articleViewModel.networkResponse.observe(viewLifecycleOwner,{
            when(it){
                is NetworkResult.Loading -> {
                    show(articlesBinding.progressBar)
                }
                is NetworkResult.Success -> {
                    hide(articlesBinding.progressBar)
                    context?.toast(it.message!!)
                }
                is NetworkResult.Failure -> {
                    hide(articlesBinding.progressBar)
                    context?.toast(it.message!!)
                }
            }
        })
    }

    private fun hideKeyBoard(){
        activity.let {
            it?.hideKeyBoard()
        }
    }
}
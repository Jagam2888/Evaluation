package com.jagad.evaluation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jagad.evaluation.data.ArticleListData
import com.jagad.evaluation.model.response.MostPopularResponse
import com.jagad.evaluation.network.NetworkResult
import com.jagad.evaluation.repositary.ArticleListRepositary
import com.jagad.evaluation.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONException
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by jagad on 1/11/2022
 */

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesRepositary: ArticleListRepositary
):ViewModel() {


    val _articleList:MutableLiveData<List<ArticleListData>> = MutableLiveData()
    val articleList:LiveData<List<ArticleListData>>
        get() = _articleList

    private val _networkResponse:MutableLiveData<NetworkResult> = MutableLiveData()
    val networkResponse:LiveData<NetworkResult>
        get() = _networkResponse

    val errorMsg = ObservableField<String>()

    fun getArticles(searchTxt:String) {
        val list = ArrayList<ArticleListData>()
        _networkResponse.value = NetworkResult.Loading()
        Couritnes.main {
            try {
                val response = articlesRepositary.getArticles(searchTxt)
                if (response.status == "OK"){
                        _networkResponse.value = NetworkResult.Success("Success")
                    if (response.response.docs.isNotEmpty()){
                        response.response.docs.forEach {
                            if (!it.headline.print_headline.isNullOrEmpty()) {
                                val articleListData = ArticleListData(
                                    it.headline.print_headline,
                                    changeDateFormat(it.pub_date,"yyyy-MM-dd'T'HH:mm:ssZZZZ","dd/MM/yyyy"),
                                )
                                list.add(articleListData)
                            }

                        }

                        _articleList.value = list
                    }
                }
            }catch (apiException:APIException){
                errorMsg.set(apiException.message!!)
                _networkResponse.value = NetworkResult.Failure(apiException.message!!)
            }catch (internet:NoInternetException){
                errorMsg.set(internet.message!!)
                _networkResponse.value = NetworkResult.Failure(internet.message!!)
            }
        }
    }

    fun getMostPopularAPI(keyWord:String){
        val list = ArrayList<ArticleListData>()
        _networkResponse.value = NetworkResult.Loading()
        Couritnes.main {
            try {
                var response: MostPopularResponse?=null
                when(keyWord){
                    Passparams.MOST_VIEWED -> response = articlesRepositary.getMostViewed()
                    Passparams.MOST_SHARED -> response = articlesRepositary.getMostShared()
                    Passparams.MOST_EMAILED -> response = articlesRepositary.getMostEmailed()
                    else -> _networkResponse.value = NetworkResult.Failure("Something Error")
                }
                if (response != null) {
                    if (response.status == "OK") {
                        if (response.results.isNotEmpty()){
                            response.results.forEach {
                                if (!it.title.isNullOrEmpty()) {
                                    val articleListData = ArticleListData(
                                        it.title,
                                        changeDateFormat(it.published_date,"yyyy-MM-dd","dd/MM/yyyy"),
                                    )
                                    list.add(articleListData)
                                }
                            }

                            _articleList.value = list
                            _networkResponse.value = NetworkResult.Success("Success")
                        }
                    }
                }
            }catch (apiException:APIException){
                errorMsg.set(apiException.message!!)
                _networkResponse.value = NetworkResult.Failure(apiException.message!!)
            }catch (internet:NoInternetException){
                errorMsg.set(internet.message!!)
                _networkResponse.value = NetworkResult.Failure(internet.message!!)
            }
        }
    }

}
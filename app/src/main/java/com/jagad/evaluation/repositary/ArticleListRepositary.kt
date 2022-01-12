package com.jagad.evaluation.repositary


import com.jagad.evaluation.model.response.MostPopularResponse
import com.jagad.evaluation.model.response.ArticleResponse
import com.jagad.evaluation.network.MyApi
import com.jagad.evaluation.util.Passparams
import com.jagad.evaluation.util.SafeAPIRequest
import javax.inject.Inject

class ArticleListRepositary @Inject constructor(
    private val myApi: MyApi,
):SafeAPIRequest() {

    suspend fun getArticles(searchTxt:String): ArticleResponse {
        return apiRequest {
            myApi.getArticles(searchTxt,Passparams.MYKEY)
        }
    }

    suspend fun getMostViewed(): MostPopularResponse {
        return apiRequest {
            myApi.getMostViewedArticles(Passparams.MYKEY)
        }
    }

    suspend fun getMostEmailed(): MostPopularResponse {
        return apiRequest {
            myApi.getMostEmailArticles(Passparams.MYKEY)
        }
    }

    suspend fun getMostShared(): MostPopularResponse {
        return apiRequest {
            myApi.getMostSharedArticles(Passparams.MYKEY)
        }
    }

}
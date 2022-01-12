package com.jagad.evaluation.network

import com.jagad.evaluation.model.response.MostPopularResponse
import com.jagad.evaluation.model.response.ArticleResponse
import com.jagad.evaluation.util.Passparams
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET(Passparams.SEARCH_ARTICLES_API)
    suspend fun getArticles(@Query("q") searchTxt:String,@Query("api-key") myKey:String):Response<ArticleResponse>

    @GET(Passparams.MOST_VIEWED_ARTICLES_API)
    suspend fun getMostViewedArticles(@Query("api-key") myKey:String):Response<MostPopularResponse>

    @GET(Passparams.MOST_EMAILED_ARTICLES_API)
    suspend fun getMostEmailArticles(@Query("api-key") myKey:String):Response<MostPopularResponse>

    @GET(Passparams.MOST_SHARED_ARTICLES_API)
    suspend fun getMostSharedArticles(@Query("api-key") myKey:String):Response<MostPopularResponse>

}
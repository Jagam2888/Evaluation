package com.jagad.evaluation.util

import android.content.Context
import com.jagad.evaluation.network.CheckInternetConnectivity

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitClientInstance @Inject constructor(){

    companion object{
        private const val BASE_URL = "https://api.nytimes.com/"
    }


    private fun createOkHttp(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(InternetConnectionInterceptor(context))
            .build()
    }

    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttp(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    class InternetConnectionInterceptor(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!CheckInternetConnectivity.isAvailable(context))
                throw NoInternetException(CheckInternetConnectivity.errorMsg)

            return chain.proceed(chain.request())
        }
    }
}
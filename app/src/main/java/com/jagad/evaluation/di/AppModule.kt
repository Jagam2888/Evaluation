package com.jagad.evaluation.di

import android.content.Context
import com.jagad.evaluation.network.MyApi
import com.jagad.evaluation.util.RetrofitClientInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by jagad on 1/11/2022
 */
@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMyApi(
        @ApplicationContext context: Context,
        retrofitClientInstance: RetrofitClientInstance
    ):MyApi{
        return retrofitClientInstance.buildApi(MyApi::class.java,context)
    }

}
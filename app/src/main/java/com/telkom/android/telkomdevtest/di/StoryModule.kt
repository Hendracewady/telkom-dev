package com.telkom.android.telkomdevtest.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Hendra Cewady on 19/01/2022.
 */

@Module
class StoryModule {
    private val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    @Singleton
    @Provides
    fun getRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): StoryApiService {
        return retrofit.create(StoryApiService::class.java)
    }
}
package com.telkom.android.telkomdevtest.di

import com.telkom.android.telkomdevtest.model.CommentResponse
import com.telkom.android.telkomdevtest.model.UserItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
interface StoryApiService {
    @GET("topstories.json")
    fun getTopStoriesId(): Call<List<Long>>?

    @GET("item/{id}")
    fun getUseritem(@Path("id") id: String): Call<UserItemResponse>?

    @GET("item/{id}")
    fun getComment(@Path("id") id: String): Call<CommentResponse>?
}
package com.telkom.android.telkomdevtest.story

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.telkom.android.telkomdevtest.di.StoryApiService
import com.telkom.android.telkomdevtest.di.StoryApplication
import com.telkom.android.telkomdevtest.model.StoryModel
import com.telkom.android.telkomdevtest.model.TopStoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class TopStoriesViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var storyService: StoryApiService

    private var _storyList: MutableLiveData<List<Long>?>
    val storyList: LiveData<List<Long>?> get() = _storyList

    init {
        (application as StoryApplication).getStoryComponent().inject(this)
        _storyList = MutableLiveData()
    }

    fun callApi(){
        val call: Call<List<Long>>? = storyService.getTopStoriesId()
        call?.enqueue(object : Callback<List<Long>> {
            override fun onResponse(call: Call<List<Long>>, response: Response<List<Long>>) {
                if (response.isSuccessful) {
                    _storyList.postValue(response.body())
                } else {
                    _storyList.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Long>>, t: Throwable) {
                _storyList.postValue(null)
            }

        })
    }

}
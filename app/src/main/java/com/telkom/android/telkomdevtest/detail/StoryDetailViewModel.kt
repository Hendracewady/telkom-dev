package com.telkom.android.telkomdevtest.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.telkom.android.telkomdevtest.di.StoryApiService
import com.telkom.android.telkomdevtest.di.StoryApplication
import com.telkom.android.telkomdevtest.model.CommentResponse
import com.telkom.android.telkomdevtest.model.UserItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class StoryDetailViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var service: StoryApiService

    private var _commentItem: MutableLiveData<CommentResponse?>
    val commentItem: LiveData<CommentResponse?> get() = _commentItem

    init {
        (application as StoryApplication).getStoryComponent().inject(this)
        _commentItem = MutableLiveData()
    }

    fun callApi(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<CommentResponse>? = service.getComment("${id}.json")
            call?.enqueue(object : Callback<CommentResponse> {
                override fun onResponse(
                    call: Call<CommentResponse>,
                    response: Response<CommentResponse>
                ) {
                    if (response.isSuccessful) {
                        _commentItem.postValue(response.body())
                    } else {
                        _commentItem.postValue(null)
                    }
                }

                override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                    _commentItem.postValue(null)
                }

            })
        }
    }
}
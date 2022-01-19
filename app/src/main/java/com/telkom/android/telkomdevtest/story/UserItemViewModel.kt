package com.telkom.android.telkomdevtest.story

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.telkom.android.telkomdevtest.di.StoryApiService
import com.telkom.android.telkomdevtest.di.StoryApplication
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
class UserItemViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var service: StoryApiService

    private var _userItem: MutableLiveData<UserItemResponse?>
    val userItem: LiveData<UserItemResponse?> get() = _userItem

    init {
        (application as StoryApplication).getStoryComponent().inject(this)
        _userItem = MutableLiveData()
    }

    fun callApi(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val call: Call<UserItemResponse>? = service.getUseritem("${id}.json")
            call?.enqueue(object : Callback<UserItemResponse> {
                override fun onResponse(
                    call: Call<UserItemResponse>,
                    response: Response<UserItemResponse>
                ) {
                    if (response.isSuccessful) {
                        _userItem.postValue(response.body())
                    } else {
                        _userItem.postValue(null)
                    }
                }

                override fun onFailure(call: Call<UserItemResponse>, t: Throwable) {
                    _userItem.postValue(null)
                }

            })
        }
    }
}
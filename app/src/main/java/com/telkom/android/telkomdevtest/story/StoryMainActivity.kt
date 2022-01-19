package com.telkom.android.telkomdevtest.story

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telkom.android.telkomdevtest.R
import com.telkom.android.telkomdevtest.detail.StoryDetailActivity
import com.telkom.android.telkomdevtest.model.UserItemResponse
import com.telkom.android.telkomdevtest.story.adapter.StoryAdapter

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class StoryMainActivity : AppCompatActivity(), StoryAdapter.StoryItemClickListener {

    private val SPAN_COLUMN_COUNT = 2
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var viewModel: TopStoriesViewModel
    private lateinit var viewModelUser: UserItemViewModel
    private var totalItem = 0
    var userItemTemporary: ArrayList<UserItemResponse> = arrayListOf()
    private lateinit var sharedPref: SharedPreferences
    private val PREFS_NAME = "LastTitleClicked"
    private val KEY_TITLE = "last-title"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.story_main_activity)

        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        initRecyclerView()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        val lastTitle = findViewById<TextView>(R.id.tv_story_title)
        lastTitle.text = getLastTitleClicked()
    }

    private fun getLastTitleClicked(): String{
        return sharedPref.getString(KEY_TITLE, "No story you viewed before").toString()
    }

    private fun saveLastTitleClicked(title: String){
        val editor = sharedPref.edit()
        editor.putString(KEY_TITLE, title)
        editor.apply()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_story)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@StoryMainActivity, SPAN_COLUMN_COUNT)
            setHasFixedSize(true)
            storyAdapter = StoryAdapter(this@StoryMainActivity)
            storyAdapter.setStoryItemClickListener(this@StoryMainActivity)
            adapter = storyAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TopStoriesViewModel::class.java]
        viewModelUser = ViewModelProvider(this)[UserItemViewModel::class.java]

        viewModel.storyList.observe(this, { model ->
            if (model != null) {

//                totalItem = model.size
//                model.map {
//                    initViewModelUser(it)
//                }
//
            // TODO: can't load all data, have problem with loop call API

                totalItem = 2
                for (i in 0..totalItem - 1) {
                    initViewModelUser(model[i])
                }

            } else {
                // error
            }
        })
        viewModel.callApi()
    }

    fun initViewModelUser(id: Long) {
        viewModelUser.userItem.observe(this, { model ->
            if (model != null) {

                if (!userItemTemporary.contains(model)) {
                    userItemTemporary.add(model)
                }

                if (userItemTemporary.size == totalItem) {
                    storyAdapter.setUpdatedData(userItemTemporary)
                }
            } else {
                // error
            }
        })
        viewModelUser.callApi(id)
    }

    override fun onStoryClicked(item: UserItemResponse) {
        saveLastTitleClicked(item.title)
        var intent = Intent(this, StoryDetailActivity::class.java)
        intent.putExtra("story-detail", item)
        startActivity(intent)
    }

}
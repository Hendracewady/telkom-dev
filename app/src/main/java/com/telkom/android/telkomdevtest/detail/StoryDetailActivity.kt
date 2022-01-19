package com.telkom.android.telkomdevtest.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telkom.android.telkomdevtest.R
import com.telkom.android.telkomdevtest.detail.adapter.CommentAdapter
import com.telkom.android.telkomdevtest.model.UserItemResponse
import com.telkom.android.telkomdevtest.util.ApplicationUtil

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class StoryDetailActivity : AppCompatActivity() {

    private lateinit var commentAdapter: CommentAdapter
    private lateinit var viewModel: StoryDetailViewModel
    private lateinit var params: UserItemResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.story_detail_activity)

        initView()
        initRecyclerView()
        initViewModel()
    }

    fun initView() {
        params = intent.extras?.get("story-detail") as UserItemResponse

        val titleTest = findViewById<TextView>(R.id.tv_story_detail_title)
        titleTest.text = params.title

        val author = findViewById<TextView>(R.id.tv_story_detail_author)
        author.text = "By: ${params.by}"


        val date = findViewById<TextView>(R.id.tv_story_detail_date)
        date.text = ApplicationUtil.convertMillisToData("dd/MM/yyyy", params.time.toLong())

        val description = findViewById<TextView>(R.id.tv_story_detail_description)
        //description.text = params.toString()
        //TODO: not have description field in item API
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_comment)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StoryDetailActivity)
            setHasFixedSize(true)
            commentAdapter = CommentAdapter()
            adapter = commentAdapter
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this)[StoryDetailViewModel::class.java]
        var commentListId = params.kids
        commentListId.map {
            showCommentData(it)
        }
    }

    fun showCommentData(id: Long){
        viewModel.commentItem.observe(this, { model ->
            if (model != null) {
                commentAdapter.addDataToAdapter(model.text)
            } else {
                // error
            }
        })
        viewModel.callApi(id)
    }

}
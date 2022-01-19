package com.telkom.android.telkomdevtest.story.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.telkom.android.telkomdevtest.R
import com.telkom.android.telkomdevtest.model.UserItemResponse

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class StoryAdapter(private val context: Context) :
    RecyclerView.Adapter<StoryAdapter.StoryViewholder>() {

    companion object {
        lateinit var listener: StoryItemClickListener
    }

    private var storyListItem: ArrayList<UserItemResponse> = ArrayList()

    fun setUpdatedData(item: ArrayList<UserItemResponse>) {
        storyListItem = item
        notifyDataSetChanged()
    }

    inner class StoryViewholder(view: View) : RecyclerView.ViewHolder(view) {
        var storyItemContainer = view.findViewById<CardView>(R.id.story_item_container)
        var storyItemTitle = view.findViewById<TextView>(R.id.tv_story_item_title)
        var storyItemComment = view.findViewById<TextView>(R.id.tv_story_item_comment_count)
        var storyItemScore = view.findViewById<TextView>(R.id.tv_story_item_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_item, parent, false)
        return StoryViewholder(view)
    }

    override fun onBindViewHolder(holder: StoryViewholder, position: Int) {
        holder.storyItemTitle.text = storyListItem[position].title
        holder.storyItemComment.text = "Total Comment: ${storyListItem[position].kids.size}"
        holder.storyItemScore.text = "Score : ${storyListItem[position].score}"
        holder.storyItemTitle.text = storyListItem[position].title
        holder.storyItemContainer.setOnClickListener {
            listener.onStoryClicked(storyListItem[position])
        }

    }

    override fun getItemCount() = storyListItem.size

    fun setStoryItemClickListener(paramListener: StoryItemClickListener) {
        listener = paramListener
    }

    interface StoryItemClickListener {
        fun onStoryClicked(item: UserItemResponse)
    }

}
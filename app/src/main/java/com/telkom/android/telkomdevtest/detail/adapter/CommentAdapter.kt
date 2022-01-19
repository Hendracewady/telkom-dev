package com.telkom.android.telkomdevtest.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telkom.android.telkomdevtest.R
import com.telkom.android.telkomdevtest.util.ApplicationUtil

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var commentList: ArrayList<String> = ArrayList()

    fun setUpdatedData(item: ArrayList<String>) {
        commentList = item
        notifyDataSetChanged()
    }

    fun addDataToAdapter(item: String) {
        if (!commentList.contains(item)) {
            commentList.add(item)
            notifyDataSetChanged()
        }
    }

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var comment = view.findViewById<TextView>(R.id.tv_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.comment.text = ApplicationUtil.convertHtmlToString(commentList[position])
    }

    override fun getItemCount() = commentList.size

}
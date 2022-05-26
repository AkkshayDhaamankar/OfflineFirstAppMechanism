package com.tsbempire.offlinecachingmechanism.presenter

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tsbempire.core.data.Comment
import com.tsbempire.offlinecachingmechanism.R
import com.tsbempire.offlinecachingmechanism.databinding.ItemCommentBinding
import java.text.SimpleDateFormat
import java.util.*

class CommentListAdapter(var comments: ArrayList<Comment>, val action: ListAction) :
    RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {

    fun updateComments(newComments: List<Comment>) {
        comments.clear()
        comments.addAll(newComments)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    // this overrides fixed recyclerview showing incorrect green color when screen is
    // brought into foreground from background
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCommentBinding.bind(view)
        private val layout = binding.commentLayout
        private val tvComment = binding.tvComment
        private val tvDate = binding.tvDate
        fun bind(comment: Comment) {
            tvComment.text = comment.comment
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(comment.updateTime)
            tvDate.text = "Last updated: ${sdf.format(resultDate)}"
            Log.d("CommentListAdapter", "isSync ${comment.isSync} name ${comment.comment}")
            when (comment.isSync) {
                1 -> tvComment.setTextColor(Color.GREEN)
                2 -> {
                    tvComment.setTextColor(Color.RED)
                    tvComment.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG}
                else -> tvComment.setTextColor(Color.GRAY)
            }
            layout.setOnClickListener {
                action.onClick(comment.id)
            }
        }
    }


}
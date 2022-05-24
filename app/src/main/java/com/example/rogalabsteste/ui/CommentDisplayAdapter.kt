package com.example.rogalabsteste.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Comment

class CommentDisplayAdapter : RecyclerView.Adapter<CommentDisplayAdapter.CommentViewHolder>() {

    private val listComments: MutableList<Comment> = mutableListOf()

    inner class CommentViewHolder(commentView: View) : RecyclerView.ViewHolder(commentView) {
        val tvCommentName: TextView = commentView.findViewById(R.id.tvCommentName)
        val tvCommentEmail: TextView = commentView.findViewById(R.id.tvCommentEmail)
        val tvCommentBody: TextView = commentView.findViewById(R.id.tvCommentBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_comment,
            parent,
            false
        )
        return CommentViewHolder(commentView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = listComments[position]

        holder.apply {
            tvCommentName.text = comment.name
            tvCommentEmail.text = comment.email
            tvCommentBody.text = comment.body
        }
    }

    override fun getItemCount(): Int = listComments.size

    fun addToComments(comments: List<Comment>) {
        listComments.addAll(comments)
        notifyDataSetChanged()
    }
}
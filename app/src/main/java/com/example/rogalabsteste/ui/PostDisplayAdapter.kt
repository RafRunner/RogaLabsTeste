package com.example.rogalabsteste.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Post

class PostDisplayAdapter(
    private val onPostClicked: (Post) -> Unit,
)
    : RecyclerView.Adapter<PostDisplayAdapter.PostViewHolder>() {

    private val listPosts: MutableList<Post> = mutableListOf()

    inner class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView) {
        val tvPostTitle: TextView = postView.findViewById(R.id.tvPostTitle)
        val tvPostBody: TextView = postView.findViewById(R.id.tvPostBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_post,
            parent,
            false
        )
        return PostViewHolder(postView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listPosts[position]

        holder.tvPostTitle.text = post.title
        holder.tvPostBody.text = post.body

        holder.itemView.setOnClickListener {
            onPostClicked(post)
        }
    }

    override fun getItemCount(): Int = listPosts.size

    fun addToPostList(posts: List<Post>) {
        listPosts.addAll(posts)
        notifyDataSetChanged()
    }
}
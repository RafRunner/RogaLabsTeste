package com.example.rogalabsteste.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Post
import com.example.rogalabsteste.services.postService

class PostDisplayActivity : AppCompatActivity() {
    private val TAG = "PostDisplayActivity"

    private val postDisplayViewModel by viewModels<PostDisplayViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PostDisplayViewModel(postService) as T
            }
        }
    }

    private val postDisplayAdapter = PostDisplayAdapter(::onClickPost)
    private var hasOngoingRequest = false
    private var currentCommentFragment: CommentDisplayFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_display)

        val self = this

        findViewById<RecyclerView>(R.id.rvPosts).apply {
            val linearLayoutManager = LinearLayoutManager(self)
            setHasFixedSize(true)
            adapter = postDisplayAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setUpScroller(this, linearLayoutManager)
        }

        postDisplayViewModel.listPosts.observe(self) {
            hasOngoingRequest = false
            postDisplayAdapter.addToPostList(it)
        }

        hasOngoingRequest = true
        postDisplayViewModel.fetchMorePosts()
    }

    fun onClickPost(post: Post) {
        currentCommentFragment = CommentDisplayFragment.newInstance(post)
        supportFragmentManager.beginTransaction()
        .add(R.id.llPosts, currentCommentFragment!!)
        .commit()
        Log.d(TAG, "Post #${post.id} Clicked!")
    }

    override fun onBackPressed() {
        if (currentCommentFragment == null) {
            super.onBackPressed()
            return
        }
        supportFragmentManager.beginTransaction().remove(currentCommentFragment!!).commit()
        currentCommentFragment = null
    }

    private fun setUpScroller(recyclerView: RecyclerView, linearLayoutManager: LinearLayoutManager) {
        recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy <= 0) return
                    if (hasOngoingRequest) return

                    val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                    val items = postDisplayAdapter.itemCount

                    if (lastVisibleItem + 4 < items || items > 100) return

                    Log.i(TAG, "Loading more posts")
                    hasOngoingRequest = true
                    postDisplayViewModel.fetchMorePosts()
                }
            }
        )
    }
}
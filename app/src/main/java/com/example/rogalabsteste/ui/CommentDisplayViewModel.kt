package com.example.rogalabsteste.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rogalabsteste.model.Comment
import com.example.rogalabsteste.model.Post
import com.example.rogalabsteste.services.CommentService
import com.example.rogalabsteste.services.commentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentDisplayViewModel(commentService: CommentService) : ViewModel() {
    private val TAG = "CommentDisplayViewModel"

    val listComments = MutableLiveData<List<Comment>>()

    private var offsetsToApply = 0

    fun fetchAllPostComments(post: Post) {
        commentService.getPostComments(post.id).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.code() != 200) {
                    Log.e(TAG, "Error fetching post`s comments: ${response.code()}")
                    return
                }

                offsetsToApply++
                listComments.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(TAG, "Error fetching post`s comments: $t")
            }
        })
    }
}
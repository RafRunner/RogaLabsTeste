package com.example.rogalabsteste.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rogalabsteste.model.Post
import com.example.rogalabsteste.services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDisplayViewModel(private val postService: PostService) : ViewModel() {
    private val TAG = "PostDisplayViewModel"

    val listPosts = MutableLiveData<List<Post>>()

    private var offsetsToApply = 0

    fun fetchMorePosts() {
        val batchSize = 10

        postService.getPosts(batchSize, batchSize * offsetsToApply).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.code() != 200) {
                    Log.e(TAG, "Error fetching posts: ${response.code()}")
                    return
                }

                offsetsToApply++
                listPosts.value = response.body()!!
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e(TAG, "Error fetching posts: $t")
            }
        })
    }
}
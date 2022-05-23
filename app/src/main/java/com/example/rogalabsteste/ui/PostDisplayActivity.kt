package com.example.rogalabsteste.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rogalabsteste.R
import com.example.rogalabsteste.model.Comment
import com.example.rogalabsteste.model.Post
import com.example.rogalabsteste.services.commentService
import com.example.rogalabsteste.services.postService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PostDisplayActivity"

class PostDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_display)

        postService.getPosts(10).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.code() != 200) {
                    Log.e(TAG, "Error fetching posts: ${response.code()}")
                    return
                }

                response.body()!!.forEach {
                    Log.d(TAG, it.toString())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e(TAG, "Error fetching posts: $t")
            }
        })

        val response = commentService.getPostComments(1).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.code() != 200) {
                    Log.e(TAG, "Error fetching comments: ${response.code()}")
                    return
                }

                response.body()!!.forEach {
                    Log.d(TAG, "Comment body: " + it.getFormattedBody())
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(TAG, "Error fetching comments: $t")
            }
        })
    }
}
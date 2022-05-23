package com.example.rogalabsteste.services

import com.example.rogalabsteste.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("/posts")
    fun getPosts(
        @Query("_limit") limit: Int = 20,
        @Query("_start") offset: Int = 0,
    ) : Call<List<Post>>
}

val postService: PostService = retrofit.create(PostService::class.java)

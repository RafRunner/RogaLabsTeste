package com.example.rogalabsteste.services

import com.example.rogalabsteste.model.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {

    @GET("/posts/{postId}/comments")
    fun getPostComments(
        @Path("postId") postId: Long,
    ): Call<List<Comment>>
}

val commentService: CommentService = retrofit.create(CommentService::class.java)

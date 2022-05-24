package com.example.rogalabsteste.model

import java.io.Serializable

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
) : Serializable
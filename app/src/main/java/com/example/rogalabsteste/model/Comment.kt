package com.example.rogalabsteste.model

import java.io.Serializable

data class Comment(
    val id: Long,
    val name: String,
    val email: String,
    val body: String,
) : Serializable
package com.example.rogalabsteste.model

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
) {
    fun getFormattedBody(): String {
        return body.replace("\n", "")
    }
}
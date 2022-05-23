package com.example.rogalabsteste.model

data class Comment(
    val id: Long,
    val name: String,
    val email: String,
    val body: String,
) {
    fun getFormattedBody(): String {
        return body.replace("\n", "")
    }
}
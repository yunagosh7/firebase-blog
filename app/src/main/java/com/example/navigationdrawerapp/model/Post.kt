package com.example.navigationdrawerapp.model

data class Post(
    val title: String? = null,
    val body: String? = null,
    val userEmail: String? = null,
    val date: String? = null,
    val likes: Long? = null,
    val category: String? = null,
    val comments: List<Comment>? = null

) {

}

package com.example.navigationdrawerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.navigationdrawerapp.R
import com.example.navigationdrawerapp.model.Post
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

interface PostListeners {
    fun navigateToPostDetail(title: String)
    fun like(email: String)
}

class PostAdapter(
    options: FirebaseRecyclerOptions<Post>,
    private val listeners: PostListeners
) : FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.bind(model, listeners)
    }
}
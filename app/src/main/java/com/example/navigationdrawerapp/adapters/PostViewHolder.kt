package com.example.navigationdrawerapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawerapp.databinding.ItemPostBinding
import com.example.navigationdrawerapp.model.Post

class PostViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPostBinding.bind(view)
    fun bind(post: Post,listeners: PostListeners) {
        with(binding) {
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
            tvPostCategory.text = "c/${post.category}"
            tvPostAuthor.text = "Publicado por ${post.userEmail!!.split("@")[0]} el ${post.date}"
            tvPostLikes.text = post.likes.toString()
            tvPostComments.text = if(post.comments == null) "Sin comentarios" else "${post.comments.size.toString()} comentarios"
            ibLike.setOnClickListener {
                listeners.like(post.title!!)
            }
            cardContainer.setOnClickListener {
                listeners.navigateToPostDetail(post.title!!)
            }
        }


    }


}
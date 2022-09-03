package com.example.instagramclone.presentation.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.databinding.PostItemBinding
import com.example.instagramclone.domain.models.models.Post
import com.squareup.picasso.Picasso

class PostViewHolder(
    val binding:PostItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post) = binding.apply{
        postUser.text = post.user_name
        titleText.text = post.post_title
        deskText.text = post.post_desc
        Picasso.get().load(post.post_image.url).into(postImage1)
    }
}
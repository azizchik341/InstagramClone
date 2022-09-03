package com.example.instagramclone.domain.models.repository

import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.models.Posts
import retrofit2.Response

interface PostRepository {
    suspend fun createPost(post: Post):Response<Post>
    suspend fun getAllPosts():Response<Posts>
}
package com.example.instagramclone.data

import com.example.instagramclone.Constans
import com.example.instagramclone.Constans.CONTENT_TYPE
import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.models.Posts
import com.example.instagramclone.domain.models.repository.PostRepository
import retrofit2.Response

object PostRepositoryImpl:PostRepository {
    override suspend fun createPost(post: Post): Response<Post> {
        return RetrofitInstanse.api.createPost(Constans.CONTENT_TYPE,post)
    }

    override suspend fun getAllPosts(): Response<Posts> {
        return RetrofitInstanse.api.getAllPost()
    }
}
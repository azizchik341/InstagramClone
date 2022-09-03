package com.example.instagramclone.domain.models.usecases

import com.example.instagramclone.data.PostRepositoryImpl
import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.repository.PostRepository

class CreatePostUseCase(private val repository: PostRepositoryImpl) {
    suspend fun execute(post: Post) = repository.createPost(post)
}
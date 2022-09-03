package com.example.instagramclone.domain.models.usecases

import com.example.instagramclone.domain.models.repository.PostRepository

class GetAllPostsUseCase(private val repository: PostRepository) {
    suspend fun execute()=repository.getAllPosts()
}
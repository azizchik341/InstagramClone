package com.example.instagramclone.domain.models.usecases

import com.example.instagramclone.data.RepositoryImpl
import com.example.instagramclone.domain.models.models.User
import com.example.instagramclone.domain.models.repository.Repository

class SignUpUseCase(private val repository: RepositoryImpl) {
    suspend fun execute(user: User) = repository.signUp(user)
}
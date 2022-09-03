package com.example.instagramclone.domain.models.usecases

import com.example.instagramclone.data.RepositoryImpl
import com.example.instagramclone.domain.models.repository.Repository

class LoginUseCase (private val repository: RepositoryImpl){
    suspend fun execute(username:String,password:String) = repository.login(username,password)
}
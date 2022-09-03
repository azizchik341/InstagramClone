package com.example.instagramclone.domain.models.repository

import com.example.instagramclone.domain.models.models.User
import retrofit2.Response

interface Repository {
suspend fun signUp(user: User):Response<User>
suspend fun login(username:String,password:String):Response<User>
}
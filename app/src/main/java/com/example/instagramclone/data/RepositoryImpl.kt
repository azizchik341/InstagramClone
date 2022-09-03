package com.example.instagramclone.data

import com.example.instagramclone.Constans.CONTENT_TYPE
import com.example.instagramclone.domain.models.models.User
import com.example.instagramclone.domain.models.repository.Repository
import retrofit2.Response

object RepositoryImpl:Repository {
    override suspend fun signUp(user: User): Response<User> {
        return RetrofitInstanse.api.signUp(
            1,CONTENT_TYPE,user
        )
    }

    override suspend fun login(username: String, password: String): Response<User> {
        return RetrofitInstanse.api.login(1,username,password)
    }

}
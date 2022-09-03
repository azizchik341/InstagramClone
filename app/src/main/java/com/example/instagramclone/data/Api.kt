package com.example.instagramclone.data

import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.models.Posts
import com.example.instagramclone.domain.models.models.User
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @POST("users")
    suspend fun signUp(
        @Header("X-Parse-Revocable-Session") session: Int,
        @Header("Content-Type") type: String,
        @Body user: User,
    ): Response<User>

        @GET("login")
        suspend fun login(
            @Header("X-Parse-Revocable-Session") session: Int,
            @Query("username")username:String,
            @Query("password")password:String
        ): Response<User>

    @POST("classes/Post")
    suspend fun createPost(
        @Header("Content-Type") type: String,
        @Body post: Post
    ):Response<Post>
    @GET("classes/Post")
    suspend fun getAllPost():Response<Posts>
}
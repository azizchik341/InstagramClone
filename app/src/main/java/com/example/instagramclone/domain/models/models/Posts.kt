package com.example.instagramclone.domain.models.models

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("results")
    val posts:List<Post>
)

package com.example.instagramclone.domain.models.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("post_title") var post_title: String,
    @SerializedName("post_description") var post_desc: String,
    @SerializedName("post_imge") var post_image: Image,
    @SerializedName("user_name") var user_name: String,
    @SerializedName("user_id") var user_id: String,
)
package com.example.instagramclone.domain.models.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("__type")
    val type: String = "File",
    val name: String,
    val url: String,
)

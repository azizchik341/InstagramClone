package com.example.instagramclone.domain.models.models

import java.io.Serializable

data class User (
    val username:String,
    val password:String,
    val email:String
        ):Serializable
package com.example.instagramclone.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.data.PostRepositoryImpl
import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.models.Posts
import com.example.instagramclone.domain.models.usecases.GetAllPostsUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private val repository = PostRepositoryImpl
    private val getAllPostsUseCase = GetAllPostsUseCase(repository)

    private val _posts:MutableLiveData<Response<Posts>> = MutableLiveData()
    val posts:LiveData<Response<Posts>> get() = _posts

    fun getAllPosts() = viewModelScope.launch{
        _posts.value = getAllPostsUseCase.execute()
    }
}
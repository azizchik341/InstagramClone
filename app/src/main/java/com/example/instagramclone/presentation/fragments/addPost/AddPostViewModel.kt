package com.example.instagramclone.presentation.fragments.addPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.data.PostRepositoryImpl
import com.example.instagramclone.domain.models.models.Post
import com.example.instagramclone.domain.models.usecases.CreatePostUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class AddPostViewModel:ViewModel() {
    private val repository = PostRepositoryImpl
    private val createPostUseCase = CreatePostUseCase(repository)

    private val _response :MutableLiveData<Response<Post>> = MutableLiveData()
    val response:LiveData<Response<Post>> get() = _response

    fun createPost(post: Post) = viewModelScope.launch {
        _response.value = createPostUseCase.execute(post)
    }
}
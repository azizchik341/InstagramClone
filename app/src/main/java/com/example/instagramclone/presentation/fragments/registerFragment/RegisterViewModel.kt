package com.example.instagramclone.presentation.fragments.registerFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.data.RepositoryImpl
import com.example.instagramclone.domain.models.models.User
import com.example.instagramclone.domain.models.usecases.SignUpUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    private val repository = RepositoryImpl
    private val signUpUseCase = SignUpUseCase(repository)


    private val _state : MutableLiveData<Response<User>> = MutableLiveData()
    val state:LiveData<Response<User>> get() = _state


    private val _error:MutableLiveData<String> = MutableLiveData()
//    val error:LiveData<String>get() =

    fun signUp(user: User) = viewModelScope.launch {
        try {
           _state.value = signUpUseCase.execute(user)

        }catch (e:Exception){
            Log.d("excep", e.message.toString())
        }
    }
}
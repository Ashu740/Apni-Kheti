package com.example.apnikheti.viewModel.authViewMovel

import androidx.lifecycle.ViewModel
import com.example.apnikheti.model.AuthRepository
import androidx.compose.runtime.livedata.observeAsState

class AuthViewModel : ViewModel() {
    private var authRepository: AuthRepository = AuthRepository()
    val authState = authRepository.authState

    fun logInClicked(email: String, password: String){
        authRepository.logIn(email, password)
    }

    fun signUpClicked(email: String, password: String){
        authRepository.singUp(email, password)
    }

    fun signOutClicked(){
        authRepository.signOut()
    }
}



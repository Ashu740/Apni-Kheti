package com.example.apnikheti.viewModel.authViewMovel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.apnikheti.model.AuthRepository
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apnikheti.model.AuthState
import com.example.apnikheti.model.data.User
import kotlinx.coroutines.launch

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

    val user = authRepository.user
    //to handle google sign in option
    fun handleGoogleSignIn(context: Context, navController: NavController){
        viewModelScope.launch {

            authRepository.googleSignIn(context).collect { result->
                result.fold(// allows to specify the actions for both success and failure
                    onSuccess = {authResult ->
                        val currentUser = authResult.user
                        if (currentUser != null) {
                            user.value = User(currentUser.uid, currentUser.displayName ?: "", currentUser.photoUrl.toString(), currentUser.email ?: "",)
                            navController.navigate("/dashboard"){
                                popUpTo("/login") {inclusive = true}
                            }
                        }
                    },
                    onFailure = {
                        Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show()
                    }

                )
            }
        }
    }
}



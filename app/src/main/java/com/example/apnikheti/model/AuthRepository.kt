package com.example.apnikheti.model

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apnikheti.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID


class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private  val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }
    private fun checkAuthStatus() {
        _authState.value = AuthState.Loading
        if(auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun logIn(email: String , password: String) {
        if(email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task->
                if(task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message?: "Something went wrong")
                }
            }
    }

    fun singUp(email : String, password : String){

        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?: "Something went wrong")
                }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


    //Sign in With Google

    suspend fun googleSignIn(context: Context): Flow<Result<AuthResult>> {
        _authState.value = AuthState.Loading

        return callbackFlow {
            try {
                //initialize credential manager
                val credentialManager : CredentialManager = CredentialManager.create(context)

                //generate unique no
                val ranNonce: String = UUID.randomUUID().toString()
                val bytes: ByteArray = ranNonce.toByteArray()
                val md: MessageDigest = MessageDigest.getInstance("SHA-256")
                val digest: ByteArray = md.digest(bytes)
                val hashedNonce: String = digest.fold("") { str, it -> str + "%02x".format(it) }

                //setup google id
                val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false) //show list every time
                    .setServerClientId(context.getString(R.string.web_client_id)) // client id
                    .setNonce(hashedNonce) // unique no
                    .setAutoSelectEnabled(true) // if one email directly
                    .build()

                // create credential req with google id option
                val request: GetCredentialRequest = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                // get the credential result from credential manager
                val result = credentialManager.getCredential(context, request)
                val credential = result.credential

                //check if received google id is valid or not
                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    //Extract the googleid Token credential
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    //create auth credential using googleid token
                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    //sign in using firebase
                    val authResult = auth.signInWithCredential(authCredential).await()

                    trySend(Result.success(authResult))

                    _authState.value = AuthState.Authenticated

                } else {
                    _authState.value = AuthState.Error("Received an invalid credential")
                }
            }catch (e: GetCredentialCancellationException) {
                _authState.value = AuthState.Error("Sign-in is cancelled")
                trySend(Result.failure(Exception("Sign-in is cancelled")))
            }catch (e : Exception) {
                _authState.value = AuthState.Error(e.toString())
            }
            awaitClose{ }
        }
    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error (val message : String) : AuthState()
}
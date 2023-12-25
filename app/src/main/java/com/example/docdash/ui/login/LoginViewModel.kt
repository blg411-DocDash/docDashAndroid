package com.example.docdash.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.LoginRequest
import com.example.docdash.services.ApiConstants
import com.example.docdash.services.BackendAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val loginStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun login(loginRequest: LoginRequest) {
        if (loginRequest.email.isNullOrEmpty()) {
            loginError.postValue("Please enter your email")
            loginStatus.postValue(false)
            return
        }
        if (loginRequest.password.isNullOrEmpty()) {
            loginError.postValue("Please enter your password")
            loginStatus.postValue(false)
            return
        }
        if (loginRequest.email == "" && loginRequest.password == "") {
            loginError.postValue("Please enter your email and password")
            loginStatus.postValue(false)
            return
        }
        viewModelScope.launch(Dispatchers.IO) { loginRequestHandler(loginRequest, loginError, loginStatus) }
        return
    }

    companion object {
        suspend fun loginRequestHandler(loginRequest: LoginRequest, loginError : MutableLiveData<String>, loginStatus : MutableLiveData<Boolean>) {
            try {
                val request = BackendAPI.backendAPI.login(loginRequest)
                request.body()?.token?.let {
                    loginStatus.postValue(true)
                    ApiConstants.TOKEN = it
                } ?: run {
                    loginError.postValue("Login failed due to invalid credentials.")
                    loginStatus.postValue(false)
                }
            } catch (e: Exception) {
                // TODO: Handle network error
                loginError.postValue("Login failed due to network error.")
                loginStatus.postValue(false)
            } finally {
                return
            }
        }
    }
}
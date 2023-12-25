package com.example.docdash.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.loginRequest

class LoginViewModel : ViewModel() {
    val user_email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val user_password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val login_error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val login_success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun login(loginRequest: loginRequest) {
        if (loginRequest.user_email.isNullOrEmpty()) {
            login_error.postValue("Please enter your email")
            return
        }
        if (loginRequest.user_password.isNullOrEmpty()) {
            login_error.postValue("Please enter your password")
            return
        }
        if (loginRequest.user_email == "" && loginRequest.user_password == "") {
            login_error.postValue("Please enter your email and password")
            return
        }

        login_success.postValue(true)
        return
    }
}
package com.example.docdash.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.requests.LoginRequest
import com.example.docdash.services.ApiConstants
import com.example.docdash.services.BackendAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val loginStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun login(loginRequest: LoginRequest) {
        if (loginRequest.email.isNullOrEmpty()) {
            loginMessage.postValue("Please enter your email")
            loginStatus.postValue(false)
            return
        }
        if (loginRequest.password.isNullOrEmpty()) {
            loginMessage.postValue("Please enter your password")
            loginStatus.postValue(false)
            return
        }
        if (loginRequest.email == "" && loginRequest.password == "") {
            loginMessage.postValue("Please enter your email and password")
            loginStatus.postValue(false)
            return
        }
        viewModelScope.launch(Dispatchers.IO) { makeLoginRequest(loginRequest, loginMessage, loginStatus) }
        return
    }

    companion object {
        suspend fun makeLoginRequest(loginRequest: LoginRequest, loginError : MutableLiveData<String>, loginStatus : MutableLiveData<Boolean>) {
            try {
                val request = BackendAPI.backendAPI.login(loginRequest)
                if(request.body()?.code == 0)
                {
                    ApiConstants.TOKEN= request.body()?.data?.token.toString()
                    ApiConstants.ROLE= request.body()?.data?.role.toString()
                    ApiConstants.NAME= request.body()?.data?.name.toString()
                    loginError.postValue("Login Success")
                    loginStatus.postValue(true)
                }
                else
                {
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
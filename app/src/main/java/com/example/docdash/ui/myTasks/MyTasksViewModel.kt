package com.example.docdash.ui.myTasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.ApiConstants
import com.example.docdash.services.BackendAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyTasksViewModel: ViewModel() {
    val activeTasks = MutableLiveData<List<TaskGetResponse>>()
    val completedTasks = MutableLiveData<List<TaskGetResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun updateActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            makeActiveTasksRequest()
        }
    }

    fun updateCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            makeCompletedTasksRequest()
        }
    }

    private suspend fun makeActiveTasksRequest() {
        try {
            val request = BackendAPI.backendAPI.getTasks("in progress", ApiConstants.EMAIL)
            if (request.body()?.code == 0) {
                activeTasks.postValue(request.body()?.data?.toList())
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            Log.d("XXX", e.toString())
            errorMessage.postValue("Failed to get tasks due to network error.")
        }
    }

    private suspend fun makeCompletedTasksRequest() {
        try {
            val request = BackendAPI.backendAPI.getTasks("closed", ApiConstants.EMAIL)
            if (request.body()?.code == 0) {
                completedTasks.postValue(request.body()?.data?.toList())
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            Log.d("XXX", e.toString())
            errorMessage.postValue("Failed to get tasks due to network error.")
        }
    }
}
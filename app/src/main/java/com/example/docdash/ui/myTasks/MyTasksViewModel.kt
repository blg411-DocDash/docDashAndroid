package com.example.docdash.ui.myTasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.ApiConstants
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock

class MyTasksViewModel: ViewModel() {
    val activeTasks = MutableLiveData<List<TaskGetResponse>>()
    val completedTasks = MutableLiveData<List<TaskGetResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun checkActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            UIstates.activeTasksMutex.withLock {
                if (!UIstates.isActiveTasksValid) {
                    makeActiveTasksRequest()
                }
            }
        }
    }

    fun checkCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            UIstates.completedTasksMutex.withLock {
                if (!UIstates.isCompletedTasksValid) {
                    makeCompletedTasksRequest()
                }
            }
        }
    }


    fun updateActiveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            UIstates.activeTasksMutex.withLock {
                makeActiveTasksRequest()
            }
        }
    }

    fun updateCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            UIstates.completedTasksMutex.withLock {
                makeCompletedTasksRequest()
            }
        }
    }

    private suspend fun makeActiveTasksRequest() {
        try {
            val request = BackendAPI.backendAPI.getTasks("in progress", ApiConstants.EMAIL)
            if (request.body()?.code == 0) {
                activeTasks.postValue(request.body()?.data?.toList())
                UIstates.isActiveTasksValid = true
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
                UIstates.isCompletedTasksValid = true
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            Log.d("ERROR OCCURED", e.toString())
            errorMessage.postValue("Failed to get tasks due to network error.")
        }
    }
}
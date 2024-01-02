package com.example.docdash.ui.taskPool

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskPoolViewModel : ViewModel() {
    val taskList = MutableLiveData<List<TaskGetResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun updateTaskList() {
        viewModelScope.launch(Dispatchers.IO) { makeTaskPoolRequest() }
    }

    private suspend fun makeTaskPoolRequest() {
        try {
            val request = BackendAPI.backendAPI.getTasks(status = "open", nurse = null)
            if (request.body()?.code == 0) {
                taskList.postValue(request.body()?.data?.toList())
                // If the task list is updated, then the task list is valid
                UIstates.isAvailableTasksValid = true
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            Log.d("XXX", e.toString())
            errorMessage.postValue("Failed to get tasks due to network error.")
        }
    }
}
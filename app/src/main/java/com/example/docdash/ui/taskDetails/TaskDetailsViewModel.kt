package com.example.docdash.ui.taskDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.requests.TaskUpdateRequest
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetailsViewModel : ViewModel() {
    // This is the data that we will fetch asynchronously
    // A listener from the activity will be notified when
    // the data is available.
    val taskDetailsLiveData = MutableLiveData<TaskGetResponse>()
    private val errorMessage = MutableLiveData<String>()
    suspend fun getTaskDetails(taskID: String) {
        // Suspend functions work in the background thread
        // and do not block the main thread, this is required
        // for a responsive UI.
        // This function will be called from the UI layer.
        try {
            // Call the backend API, this is a suspend function
            // so it will not block the main thread while doing
            // network operations.
            val request = BackendAPI.backendAPI.getTask(taskID)
            if (request.body()?.code == 0) {
                // When the data is ready, notify the UI layer
                taskDetailsLiveData.postValue(request.body()?.data)
            } else {
                // Error handling
                errorMessage.postValue("Failed, incorrect credentials!")
            }
        } catch (e: Exception) {
            // Exception handling
            errorMessage.postValue("Network Error!")
        }
    }

    fun takeTask(){
        viewModelScope.launch(Dispatchers.IO) {
            makeTakeTaskRequest()
        }
    }

    fun completeTask(){
        viewModelScope.launch(Dispatchers.IO) {
            makeCompleteTaskRequest()
        }
    }

    private suspend fun makeTakeTaskRequest(){
        try {
            val request = BackendAPI.backendAPI.updateTask(TaskUpdateRequest(
                taskDetailsLiveData.value?.id,
                "in progress",
                ))
            if (request.body()?.code == 0) {
                // When the data is ready, notify the UI layer
                taskDetailsLiveData.value?.status = "in progress"
                taskDetailsLiveData.postValue(taskDetailsLiveData.value)
                // VERY IMPORTANT!
                // SINCE YOU HAVE TAKEN THE TASK, YOU NEED TO UPDATE THE TASK POOL & MY TASKS
                // OTHERWISE, THE TASK WILL STILL BE SHOWN IN THE TASK POOL & WILL NOT BE SHOWN IN THE MY TASKS
                UIstates.isAvailableTasksValid = false
                UIstates.isActiveTasksValid = false
            } else {
                // Error handling
                errorMessage.postValue("Failed, unable to take task!")
            }
        } catch (e: Exception) {
            // Exception handling
            errorMessage.postValue("Network Error!")
        }
    }

    private suspend fun makeCompleteTaskRequest(){
        try {
            val request = BackendAPI.backendAPI.updateTask(TaskUpdateRequest(
                taskDetailsLiveData.value?.id,
                "closed",
                ))
            if (request.body()?.code == 0) {
                // When the data is ready, notify the UI layer
                taskDetailsLiveData.value?.status = "closed"
                taskDetailsLiveData.postValue(taskDetailsLiveData.value)
                // VERY IMPORTANT!
                // SINCE YOU HAVE COMPLETED THE TASK, YOU NEED TO UPDATE THE MY TASKS
                // OTHERWISE, THE TASK WILL STILL BE SHOWN IN THE MY TASKS AS ACTIVE
                UIstates.isActiveTasksValid = false
                UIstates.isCompletedTasksValid = false
            } else {
                // Error handling
                errorMessage.postValue("Failed, unable to complete task!")
            }
        } catch (e: Exception) {
            // Exception handling
            errorMessage.postValue("Network Error!")
        }
    }

    fun getTaskDetailsFromJson(jsonData: String) {
        // This function will be called from the UI layer.
        // This function will parse the JSON data and
        // notify the UI layer when the data is ready.
        val gson = Gson()
        val taskDetails = gson.fromJson(jsonData, TaskGetResponse::class.java)
        taskDetailsLiveData.postValue(taskDetails)
    }
}
package com.example.docdash.ui.taskDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.utils.DateTimeHandler
import com.example.docdash.services.BackendAPI
import com.google.gson.Gson

class TaskDetailsViewModel : ViewModel() {
    // This is the data that we will fetch asynchronously
    // A listener from the activity will be notified when
    // the data is available.
    val taskDetailsLiveData = MutableLiveData<TaskGetResponse>()
    val errorMessage = MutableLiveData<String>()
    suspend fun getTaskDetails(taskID: String) {
        // Suspend functions work in the backgruond thread
        // and do not block the main thread, this is required
        // for a responsive UI.
        // This function will be called from the UI layer.
        try {
            // Call the bakend API, this is a suspend function
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

    suspend fun takeTask(taskID: String){

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
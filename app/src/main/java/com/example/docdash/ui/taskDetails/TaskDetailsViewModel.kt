package com.example.docdash.ui.taskDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.TaskListItem
import com.example.docdash.utils.DateTimeHandler
import com.example.docdash.services.BackendAPI

class TaskDetailsViewModel : ViewModel() {
    // This is the data that we will fetch asynchronously
    // A listener from the activity will be notified when
    // the data is available.
    val taskDetailsLiveData = MutableLiveData<TaskListItem>()
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
                // Update the data in the background thread
                // so that the UI layer can be notified.

                // These data can be filled by the backend API, then we need to make more requests
                val requestData = request.body()?.data!!
                val taskData = TaskListItem(
                    requestData.id ?: "",
                    requestData.information ?: "",
                    DateTimeHandler.epochSecondsToDateTime(requestData.deadline ?: 0.0),
                    requestData.status ?: "",
                    "-not implemented-",
                    null,
                    null,
                    requestData.entry_id ?: "",
                    null,
                    null
                )

                // Get the patient information by the entry id
                val entryRequest = BackendAPI.backendAPI.getPatientEntry(taskData.entryID)
                if (entryRequest.body()?.code == 0) {
                    val entryData = entryRequest.body()?.data!![0]
                    taskData.tckn = entryData.tckn
                    taskData.room = entryData.room
                }

                // Will get the patient name by the tckn
                val patientRequest = BackendAPI.backendAPI.getPatient(taskData.tckn!!)
                if (patientRequest.body()?.code == 0) {
                    val patientData = patientRequest.body()?.data!!
                    taskData.patient = patientData.name
                }

                // TODO get test information when backend ready

                // When the data is ready, notify the UI layer
                taskDetailsLiveData.postValue(taskData)
            } else {
                // Error handling
                errorMessage.postValue("Failed, incorrect credentials!")
            }
        } catch (e: Exception) {
            // Exception handling
            errorMessage.postValue("Network Error!")
        }
    }
}
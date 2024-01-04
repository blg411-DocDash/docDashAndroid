package com.example.docdash.ui.patientDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.response.PatientGetResponse
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.data.serviceData.response.TestGetResponse
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock

class PatientDetailsViewModel : ViewModel(){
    val taskDetailsLiveData = MutableLiveData<TaskGetResponse>()
    val patientDetailsLiveData = MutableLiveData<PatientGetResponse?>()
    var patientTests: List<TestGetResponse>? = emptyList()
    val errorMessage = MutableLiveData<String>()

    fun patientTestsList(tckn: String?){
        viewModelScope.launch(Dispatchers.IO) {
            UIstates.availablePatientTestsMutex.withLock {
                getPatientTests(tckn = tckn)
                Log.d("Patient", "Patient Tests from view: $patientTests")
            }
        }
    }
    suspend fun getPatientTests(tckn: String?) {
        // Suspend functions work in the background thread
        // and do not block the main thread, this is required
        // for a responsive UI.
        // This function will be called from the UI layer.

        try {
            // Call the backend API, this is a suspend function
            // so it will not block the main thread while doing
            // network operations.
            val request = BackendAPI.backendAPI.getPatientTests(tckn = tckn)
            if (request.body()?.code == 0) {
                // When the data is ready, notify the UI layer

                patientTests = (request.body()?.data)
                UIstates.isAvailablePatientTestsValid = true
            } else {
                // Error handling
                errorMessage.postValue("Failed, incorrect credentials!")
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
        try {
            val taskDetails = gson.fromJson(jsonData, TaskGetResponse::class.java)
            taskDetailsLiveData.postValue(taskDetails)
            patientDetailsLiveData.postValue(taskDetails.patient)
        } catch (e: Exception) {
            Log.e("DeserializationError", "Error parsing JSON data: $jsonData", e)
        }
    }
}
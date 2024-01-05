package com.example.docdash.ui.requiredTests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.data.serviceData.response.TestGetResponse
import com.google.gson.Gson

class RequiredTestsViewModel: ViewModel() {
    val taskDetailsLiveData = MutableLiveData<TaskGetResponse>()
    val testList = MutableLiveData<List<TestGetResponse>?>()
    val errorMessage = MutableLiveData<String>()
    fun getTaskDetailsFromJson(jsonData: String) {

        // This function will be called from the UI layer.
        // This function will parse the JSON data and
        // notify the UI layer when the data is ready.
        val gson = Gson()
        try {
            val taskDetails = gson.fromJson(jsonData, TaskGetResponse::class.java)
            taskDetailsLiveData.postValue(taskDetails)
            testList.postValue(taskDetails.tests)
        } catch (e: Exception) {
            Log.e("DeserializationError", "Error parsing JSON data: $jsonData", e)
        }
    }
}
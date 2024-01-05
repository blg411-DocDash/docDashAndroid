package com.example.docdash.ui.requiredTests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.serviceData.requests.TestResultUpdateRequest
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.data.serviceData.response.TestGetResponse
import com.example.docdash.services.BackendAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequiredTestsViewModel: ViewModel() {
    val taskDetailsLiveData = MutableLiveData<TaskGetResponse>()
    val testList = MutableLiveData<List<TestGetResponse>?>()
    val errorMessage = MutableLiveData<String>()

    fun updateTestResult(id: String?, result: String?){
        val requestBody = TestResultUpdateRequest(id = id, result = result)
        viewModelScope.launch(Dispatchers.IO) {
            sendTestResult(body = requestBody)
        }
    }
    suspend fun sendTestResult(body: TestResultUpdateRequest){
        try{

            val request = BackendAPI.backendAPI.updateTestResult(body)
        }
        catch (e:Exception) {
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
            Log.d("Required Tests", "Tests: ${taskDetails.tests?.toList()}")
            testList.postValue(taskDetails.tests)
        } catch (e: Exception) {
            Log.e("DeserializationError", "Error parsing JSON data: $jsonData", e)
        }
    }
}
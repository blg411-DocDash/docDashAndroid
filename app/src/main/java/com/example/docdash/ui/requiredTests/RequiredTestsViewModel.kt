package com.example.docdash.ui.requiredTests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.serviceData.response.TestGetResponse
import com.google.gson.Gson

class RequiredTestsViewModel: ViewModel() {
    val testList = MutableLiveData<List<TestGetResponse>>()
    val taskID = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    fun getTestsListFromJson(jsonData: String?){
        val gson = Gson()
        val testsList = gson.fromJson(jsonData, Array<TestGetResponse>::class.java).toList()
        testList.postValue(testsList)
    }
}
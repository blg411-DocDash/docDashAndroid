package com.example.docdash.ui.requiredTests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.serviceData.response.TestGetResponse

class RequiredTestsViewModel: ViewModel() {
    val testList = MutableLiveData<List<TestGetResponse>>()
    val errorMessage = MutableLiveData<String>()
}
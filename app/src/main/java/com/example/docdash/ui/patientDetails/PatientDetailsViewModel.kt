package com.example.docdash.ui.patientDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.serviceData.response.PatientGetResponse
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.google.gson.Gson

class PatientDetailsViewModel : ViewModel(){
    val taskDetails = MutableLiveData<TaskGetResponse>()
    val patientDetails = MutableLiveData<PatientGetResponse>()
    private val errorMessage = MutableLiveData<String>()

}
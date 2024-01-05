package com.example.docdash.ui.patientDetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.docdash.ui.theme.DocDashTheme
import com.google.gson.Gson

class PatientDetailsActivity: ComponentActivity() {

    private val viewModel: PatientDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        }
        else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }

        if(intent.getStringExtra("tckn") != null){
            viewModel.patientTestsList(intent.getStringExtra("tckn")!!)
        }
        else{
            viewModel.errorMessage.postValue("Failed, patient is not available!")
        }


        setContent {
            DocDashTheme {
                PatientDetails(viewModel)
            }
        }

        viewModel.patientTests.observe(this) {
            setContent {
                DocDashTheme {
                    PatientDetails(viewModel)
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getString("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(savedInstanceState.getString("taskDetails")!!)
        }
        else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }

        if(savedInstanceState.getString("tckn") != null){
            viewModel.patientTestsList(savedInstanceState.getString("tckn")!!)
        }
        else{
            viewModel.errorMessage.postValue("Failed, patient is not available!")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val gson = Gson()
        if (viewModel.taskDetailsLiveData.value != null) {
            val taskDetails = gson.toJson(viewModel.taskDetailsLiveData.value)
            outState.putString("taskDetails", taskDetails)
        }
        if (viewModel.patientTests.value != null) {
            outState.putString("tckn", viewModel.patientTests.value.toString())
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        }
        else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }

        if(intent?.getStringExtra("tckn") != null){
            viewModel.patientTestsList(intent.getStringExtra("tckn")!!)
        }
        else{
            viewModel.errorMessage.postValue("Failed, patient is not available!")
        }
    }

}
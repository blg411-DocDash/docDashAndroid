package com.example.docdash.ui.patientDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.ui.taskDetails.TaskDetailsViewModel
import com.example.docdash.ui.theme.DocDashTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientDetailsActivity: ComponentActivity() {

    private val viewModel: PatientDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
            val tckn = viewModel.patientDetailsLiveData.value?.tckn?: "78099697380";
            viewModel.patientTestsList(tckn= tckn)
            // Log.d("Patient", "Patient Tests from activity: ${viewModel.patientTests}")
        }
        else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }


        setContent {
            DocDashTheme {
                PatientDetails(viewModel)
            }
        }
    }

}
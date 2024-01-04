package com.example.docdash.ui.patientDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.docdash.ui.taskDetails.TaskDetailsViewModel
import com.example.docdash.ui.theme.DocDashTheme

class PatientDetailsActivity: ComponentActivity() {

    private val viewModel: PatientDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                PatientDetails(viewModel)
            }
        }
    }
}
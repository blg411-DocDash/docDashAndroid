package com.example.docdash.ui.patientDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.docdash.ui.theme.DocDashTheme

class PatientDetailsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                PatientDetails()
            }
        }
    }
}
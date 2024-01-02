package com.example.docdash.ui.requiredTests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.docdash.ui.theme.DocDashTheme

class RequiredTestsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                RequiredTests()
            }
        }
    }
}
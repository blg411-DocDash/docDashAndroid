package com.example.docdash.ui.taskDetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.ui.theme.DocDashTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TaskDetailsActivity : ComponentActivity() {
    val viewModel: TaskDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initially, construct the screen with the data coming from intent
        intent.getStringExtra("taskDetails")?.let {
            viewModel.getTaskDetailsFromJson(it)
        }

        setContent {
            DocDashTheme {
                // Initially passing null to construct the screen without data, using the placeholder
                TaskDetails(viewModel.taskDetailsLiveData.value!!)
            }
        }


    }
}

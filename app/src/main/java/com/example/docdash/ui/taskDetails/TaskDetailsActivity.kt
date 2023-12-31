package com.example.docdash.ui.taskDetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.ui.theme.DocDashTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TaskDetailsActivity : ComponentActivity() {
    val viewModel: TaskDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme{
                // Initially passing null to construct the screen without data, using the placeholder
                TaskDetails(TaskDetailsPlaceHolder.taskDetails) }
        }

        // when the data is available, update the UI
        viewModel.taskDetailsLiveData.observe(this) {
            // Update the UI when the data is available
            setContent {
                DocDashTheme {
                    TaskDetails(it)
                }
            }
        }

        // Observe the error message from the view model
        viewModel.errorMessage.observe(this) {
            // Show the error message as a toast (popup message)
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        // Fetching the data from the backend using the intent extra passed from the previous activity
        // Also, use the IO thread to fetch the data and update the UI thread when the data is available
        lifecycleScope.launch(Dispatchers.IO) { viewModel.getTaskDetails(intent.getStringExtra("taskID")!!) }
    }
}

package com.example.docdash.ui.taskDetails


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.ui.theme.DocDashTheme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskDetailsActivity : ComponentActivity() {

    private val viewModel: TaskDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initially, construct the screen with the data coming from intent
        if (intent.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        } else {
            // If the intent does not contain the data, then fetch it from the backend
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getTaskDetails(intent.getStringExtra("taskID")!!)
            }
        }

        setContent {
            DocDashTheme {
                // Passing the view model to the composable function, to be able to fill in the screen with data
                TaskDetails(viewModel)
            }
        }

        // Update the screen when the data is updated
        viewModel.taskDetailsLiveData.observe(this) {
            setContent {
                DocDashTheme {
                    TaskDetails(viewModel)
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // These are overridden to restore the state of the activity when it is recreated
        if (savedInstanceState.getString("taskDetails") != null) {
            val taskDetails = Gson().fromJson(
                savedInstanceState.getString("taskDetails"),
                TaskGetResponse::class.java
            )
            viewModel.taskDetailsLiveData.postValue(taskDetails)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // These are overridden to save the state of the activity when it is destroyed
        outState.putString("taskDetails", Gson().toJson(viewModel.taskDetailsLiveData.value))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // This is overridden to update the screen when the intent is changed
        if (intent?.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        } else {
            val taskID = intent?.getStringExtra("taskID")

            if (taskID != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.getTaskDetails(taskID)
                }
            }
        }
    }
}

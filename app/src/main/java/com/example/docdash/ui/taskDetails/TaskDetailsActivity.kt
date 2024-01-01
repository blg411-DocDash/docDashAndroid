package com.example.docdash.ui.taskDetails

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.ui.taskPool.TaskPoolActivity
import com.example.docdash.ui.theme.DocDashTheme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TaskDetailsActivity : ComponentActivity() {

    private val viewModel: TaskDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initially, construct the screen with the data coming from intent
        if (intent.getStringExtra("taskDetails") != null) {
            val taskDetails = Gson().fromJson(
                intent.getStringExtra("taskDetails"),
                TaskGetResponse::class.java)
            viewModel.taskDetailsLiveData.postValue(taskDetails)
        }

        setContent {
            DocDashTheme {
                // Initially passing null to construct the screen without data, using the placeholder
                TaskDetails(viewModel.taskDetailsLiveData.value!!)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // These are overriden to restore the state of the activity when it is recreated
        if (savedInstanceState.getString("taskDetails") != null) {
            val taskDetails = Gson().fromJson(
                savedInstanceState.getString("taskDetails"),
                TaskGetResponse::class.java)
            viewModel.taskDetailsLiveData.postValue(taskDetails)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // These are overriden to save the state of the activity when it is destroyed
        outState.putString("taskDetails", Gson().toJson(viewModel.taskDetailsLiveData.value))
    }
}

package com.example.docdash.ui.requiredTests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.ui.taskDetails.TaskDetailsViewModel
import com.example.docdash.ui.theme.DocDashTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequiredTestsActivity: ComponentActivity() {
    private val viewModel: RequiredTestsViewModel by viewModels()
    private val taskViewModel: TaskDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getStringExtra("taskDetails") != null) {
            taskViewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
            viewModel.testList.postValue(taskViewModel.taskDetailsLiveData.value?.tests.orEmpty())

        }
        else {
            // If the intent does not contain the data, then fetch it from the backend
            lifecycleScope.launch(Dispatchers.IO) {
                taskViewModel.getTaskDetails(intent.getStringExtra("taskID")!!)
                viewModel.testList.postValue(taskViewModel.taskDetailsLiveData.value?.tests.orEmpty())
            }
        }
        setContent {
            DocDashTheme {
                RequiredTests(viewModel)
            }
        }
        viewModel.testList.observe(this) {
            setContent {
                DocDashTheme {
                    RequiredTests(viewModel)
                }
            }
        }
    }
}
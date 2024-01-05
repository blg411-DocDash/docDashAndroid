package com.example.docdash.ui.requiredTests

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.docdash.ui.theme.DocDashTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequiredTestsActivity: ComponentActivity() {
    private val viewModel: RequiredTestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        }
        else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // These are overridden to restore the state of the activity when it is recreated
        if (intent?.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        } else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (intent?.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        } else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // This is overridden to update the screen when the intent is changed
        if (intent?.getStringExtra("taskDetails") != null) {
            viewModel.getTaskDetailsFromJson(intent.getStringExtra("taskDetails")!!)
        } else {
            viewModel.errorMessage.postValue("Failed, task is not available!")
        }
    }
}
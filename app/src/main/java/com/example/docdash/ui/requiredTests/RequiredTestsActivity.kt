package com.example.docdash.ui.requiredTests

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.docdash.ui.theme.DocDashTheme
import com.google.gson.Gson

class RequiredTestsActivity: ComponentActivity() {
    private val viewModel: RequiredTestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Read the data from the intent
        val taskIdFromIntent = intent.getStringExtra("taskID")
        if (taskIdFromIntent != null){
            viewModel.taskID.postValue(taskIdFromIntent)
        }
        val requiredTestsFromIntent = intent.getStringExtra("requiredTests")
        if (requiredTestsFromIntent != null){
            viewModel.getTestsListFromJson(requiredTestsFromIntent)
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
        val taskIdFromBundle = savedInstanceState.getString("taskID")
        if (taskIdFromBundle != null){
            viewModel.taskID.postValue(taskIdFromBundle)
        }
        val requiredTestsFromBundle = savedInstanceState.getString("requiredTests")
        if (requiredTestsFromBundle != null){
            viewModel.getTestsListFromJson(requiredTestsFromBundle)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // These are overridden to save the state of the activity when it is destroyed
        outState.putString("requiredTests", Gson().toJson(viewModel.testList.value))
        outState.putString("taskID", viewModel.taskID.value)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // This is overridden to update the screen when the intent is changed
        val taskIdFromIntent = intent?.getStringExtra("taskID")
        if (taskIdFromIntent != null){
            viewModel.taskID.postValue(taskIdFromIntent)
        }
        val requiredTestsFromIntent = intent?.getStringExtra("requiredTests")
        if (requiredTestsFromIntent != null){
            viewModel.getTestsListFromJson(requiredTestsFromIntent)
        }
    }
}
package com.example.docdash.ui.taskPool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.databinding.ActivityTaskPoolBinding
import com.example.docdash.ui.UIstates
import com.example.docdash.ui.myTasks.MyTasksAcitivity
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.google.gson.Gson

class TaskPoolActivity : AppCompatActivity(), TaskPoolInterface {
    private val viewModel: TaskPoolViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var binding: ActivityTaskPoolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_pool)

        binding = ActivityTaskPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.taskPoolRW)
        taskAdapter = TaskAdapter(emptyList(), this)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.taskList.observe(this) {
            taskAdapter.updateTaskList(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        // These are the bottom navigation bar buttons
        binding.buttonMyTasks.setOnClickListener {
            val myTasksPage = Intent(this, MyTasksAcitivity::class.java)
            // Without this flag, the activity will be created again, instead of being restored
            myTasksPage.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(myTasksPage)
        }

        binding.swipeRefreshTaskPool.setOnRefreshListener {
            viewModel.updateTaskList()
            binding.swipeRefreshTaskPool.isRefreshing = false
        }

        // Update the task list when the activity is created, not restored
        // Update the task list when the Uıstates.isAvailableTasksValid is false
        if (savedInstanceState == null || !UIstates.isAvailableTasksValid)
        {
            viewModel.updateTaskList()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the task list to the bundle, so that it can be restored when the activity is recreated
        if (viewModel.taskList.value != null) {
            outState.putString("taskList", Gson().toJson(viewModel.taskList.value))
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore the task list from the bundle
        if (savedInstanceState.getString("taskList") != null) {
            val taskList = Gson().fromJson(
                savedInstanceState.getString("taskList"),
                Array<TaskGetResponse>::class.java
            ).toList()
            viewModel.taskList.postValue(taskList)
        }
    }

    override fun onResume() {
        Log.d("TaskPoolActivity", "onResume")
        super.onResume()
        // Update the task list when the activity is resumed, and ui states are invalid
        if (!UIstates.isAvailableTasksValid) {
            viewModel.updateTaskList()
        }
    }

    override fun onClickTask(position: Int) {
        // Intent is required to start another activity
        val taskDetailsPage = Intent(this, TaskDetailsActivity::class.java)
        // You can pass data to the activity with putExtra, they need to be basic types (string, int, etc.)
        val gson = Gson()
        gson.toJson(viewModel.taskList.value?.get(position))?.let {
            taskDetailsPage.putExtra("taskDetails", it)
        }
        taskDetailsPage.putExtra("taskID", viewModel.taskList.value?.get(position)?.id)
        startActivity(taskDetailsPage)
    }
}
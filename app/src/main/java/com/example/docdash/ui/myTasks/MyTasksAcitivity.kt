package com.example.docdash.ui.myTasks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.databinding.ActivityMyTasksAcitivityBinding
import com.example.docdash.ui.UIstates
import com.example.docdash.ui.logout.LogoutActivity
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.ui.taskPool.TaskPoolActivity
import com.google.gson.Gson

class MyTasksAcitivity : AppCompatActivity(), MyTasksInterface {
    private lateinit var binding: ActivityMyTasksAcitivityBinding
    private lateinit var activeTasksAdapter: MyTasksAdapter
    private lateinit var completedTasksAdapter: MyTasksAdapter
    private val viewModel: MyTasksViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks_acitivity)

        binding = ActivityMyTasksAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Using the task adapter from task pool since the ui is the same
        val activeTasksRV = binding.activeTasksRW
        activeTasksAdapter = MyTasksAdapter(emptyList(), this, MyTaskType.IN_PROGRESS)
        activeTasksRV.adapter = activeTasksAdapter
        activeTasksRV.layoutManager = LinearLayoutManager(this)

        val completedTasksRV = binding.completedTasksRW
        completedTasksAdapter = MyTasksAdapter(emptyList(), this, MyTaskType.COMPLETED)
        completedTasksRV.adapter = completedTasksAdapter
        completedTasksRV.layoutManager = LinearLayoutManager(this)

        viewModel.activeTasks.observe(this) {
            activeTasksAdapter.updateTaskList(it)
        }

        viewModel.completedTasks.observe(this) {
            completedTasksAdapter.updateTaskList(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        binding.buttonTaskPool1.setOnClickListener {
            // Go to task pool
            val taskPoolPage = Intent(this, TaskPoolActivity::class.java)
            // Without this flag, the activity will be created again, instead of being restored
            taskPoolPage.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(taskPoolPage)
        }

        // Swipe to refresh actions
        binding.swipeRefreshActiveTasks.setOnRefreshListener {
            viewModel.updateActiveTasks()
            binding.swipeRefreshActiveTasks.isRefreshing = false
        }

        binding.swipeRefreshCompletedTasks.setOnRefreshListener {
            viewModel.updateCompletedTasks()
            binding.swipeRefreshCompletedTasks.isRefreshing = false
        }

        binding.profileButton1.setOnClickListener {
            // Go to profile
            val profilePage = Intent(this, LogoutActivity::class.java)
            startActivity(profilePage)
        }

        // Update the task list when the activity is created, not restored
        if (savedInstanceState == null) {
            viewModel.updateActiveTasks()
            viewModel.updateCompletedTasks()
        } else {
            // Update the task list when the state is invalid
            if (!UIstates.isActiveTasksValid) {
                viewModel.updateActiveTasks()
            }
            if (!UIstates.isCompletedTasksValid) {
                viewModel.updateCompletedTasks()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the task list to the bundle, so that it can be restored when the activity is recreated
        if (viewModel.activeTasks.value!= null) {
            outState.putString("activeTasks", Gson().toJson(viewModel.activeTasks.value))
        }
        if(viewModel.completedTasks.value != null) {
            outState.putString("completedTasks", Gson().toJson(viewModel.completedTasks.value))
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore the task list from the bundle
        if (savedInstanceState.getString("activeTasks") != null) {
            val taskList = Gson().fromJson(
                savedInstanceState.getString("activeTasks"),
                Array<TaskGetResponse>::class.java
            ).toList()
            viewModel.activeTasks.postValue(taskList)
        }
        if (savedInstanceState.getString("completedTasks") != null) {
            val taskList = Gson().fromJson(
                savedInstanceState.getString("completedTasks"),
                Array<TaskGetResponse>::class.java
            ).toList()
            viewModel.completedTasks.postValue(taskList)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update the task list when the activity is resumed, not created
        // Update the task list when the state is invalid
        viewModel.checkActiveTasks()
        viewModel.checkCompletedTasks()
    }

    override fun onClick(position: Int, type: MyTaskType) {
        val taskDetailsPage = Intent(this, TaskDetailsActivity::class.java)
        taskDetailsPage.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        // You can pass data to the activity with putExtra, they need to be basic types (string, int, etc.)
        val gson = Gson()
        if (type == MyTaskType.IN_PROGRESS) {
            gson.toJson(viewModel.activeTasks.value?.get(position))?.let {
                taskDetailsPage.putExtra("taskDetails", it)
            }
            taskDetailsPage.putExtra("taskID", viewModel.activeTasks.value?.get(position)?.id)
        } else {
            gson.toJson(viewModel.completedTasks.value?.get(position))?.let {
                taskDetailsPage.putExtra("taskDetails", it)
            }
            taskDetailsPage.putExtra("taskID", viewModel.completedTasks.value?.get(position)?.id)
        }
        startActivity(taskDetailsPage)
    }
}
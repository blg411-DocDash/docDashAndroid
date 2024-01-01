package com.example.docdash.ui.myTasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.databinding.ActivityMyTasksAcitivityBinding
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.ui.taskPool.TaskAdapter
import com.example.docdash.ui.taskPool.TaskPoolActivity
import com.example.docdash.ui.taskPool.TaskPoolInterface
import com.google.gson.Gson

class MyTasksAcitivity : AppCompatActivity(), TaskPoolInterface {
    private lateinit var binding: ActivityMyTasksAcitivityBinding
    private lateinit var activeTasksAdapter: TaskAdapter
    private lateinit var completedTasksAdapter: TaskAdapter
    private val viewModel: MyTasksViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks_acitivity)

        binding = ActivityMyTasksAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Using the task adapter from task pool since the ui is the same
        val activeTasksRV = binding.activeTasksRW
        activeTasksAdapter = TaskAdapter(emptyList(), this)
        activeTasksRV.adapter = activeTasksAdapter
        activeTasksRV.layoutManager = LinearLayoutManager(this)

        val completedTasksRV = binding.completedTasksRW
        completedTasksAdapter = TaskAdapter(emptyList(), this)
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

        binding.buttonMyTasks1.setOnClickListener {
            // Refresh the task list
            viewModel.updateActiveTasks()
            viewModel.updateCompletedTasks()
        }
        binding.buttonTaskPool1.setOnClickListener {
            // Go to task pool
            val myTasksPage = Intent(this, TaskPoolActivity::class.java)
            startActivity(myTasksPage)
        }

        // Update the task list when the activity is created, not resored
        if (savedInstanceState == null)
        {
            viewModel.updateActiveTasks()
            viewModel.updateCompletedTasks()
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

    override fun onClickTask(position: Int) {
        // Go to task details
        // TODO solve the problem of having two different task rws
    }
}
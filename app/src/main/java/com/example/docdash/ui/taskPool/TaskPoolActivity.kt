package com.example.docdash.ui.taskPool

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.databinding.ActivityTaskPoolBinding
import com.example.docdash.ui.myTasks.MyTasksAcitivity
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.google.gson.Gson
import com.example.docdash.data.serviceData.response.TaskGetResponse

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
            val myTasksPage = Intent(this, MyTasksAcitivity::class.java).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(myTasksPage)
        }

        binding.buttonTaskPool.setOnClickListener {
            // refresh the task list
            viewModel.updateTaskList()
        }

        // Update the task list when the activity is created, not resored
        if (savedInstanceState == null)
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
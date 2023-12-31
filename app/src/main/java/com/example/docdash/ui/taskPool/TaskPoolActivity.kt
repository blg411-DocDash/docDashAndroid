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
            startActivity(myTasksPage)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateTaskList()
    }

    override fun onClickTask(position: Int) {
        val taskDetailsPage = Intent(this, TaskDetailsActivity::class.java)
        taskDetailsPage.putExtra("taskID", viewModel.taskList.value?.get(position)?.taskID)
        startActivity(taskDetailsPage)
    }
}
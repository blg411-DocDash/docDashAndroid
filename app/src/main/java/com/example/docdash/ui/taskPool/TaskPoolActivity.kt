package com.example.docdash.ui.taskPool

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.databinding.ActivityTaskPoolBinding
import com.example.docdash.services.ApiConstants

class TaskPoolActivity : AppCompatActivity() {
    private val viewModel: TaskPoolViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var binding: ActivityTaskPoolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_pool)

        binding = ActivityTaskPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.taskPoolRW)
        taskAdapter = TaskAdapter(emptyList())
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.taskList.observe(this) {
            taskAdapter.updateTaskList(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        // viewModel.updateTaskList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateTaskList()
    }
}
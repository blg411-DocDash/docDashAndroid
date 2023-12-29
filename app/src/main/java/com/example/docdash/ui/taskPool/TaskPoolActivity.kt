package com.example.docdash.ui.taskPool

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.databinding.ActivityTaskPoolBinding

class TaskPoolActivity : AppCompatActivity() {
    private val viewModel: TaskPoolViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var binding: ActivityTaskPoolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_pool)

        // TODO check savedinstatncestate

        binding = ActivityTaskPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.taskPoolRW)
        taskAdapter = TaskAdapter(emptyList())
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getTaskList().observe(this) {
            taskAdapter.updateTaskList(it)
        }

        viewModel.updateTaskList()
    }
}
package com.example.docdash.ui.taskPool

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.data.TaskItem

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dueDate: TextView = view.findViewById(R.id.taskDue)
    val taskID: TextView = view.findViewById(R.id.taskNo)
    val taskDescription: TextView = view.findViewById(R.id.taskDescription)
}

class TaskAdapter(private var taskList: List<TaskItem>) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_pool_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to views
        holder.dueDate.text = taskList[position].deadline
        holder.taskID.text = taskList[position].entry_ID
        holder.taskDescription.text = taskList[position].information
    }

    fun updateTaskList(newTaskList: List<TaskItem>) {
        this.taskList = newTaskList
        // TODO Check alternatives to notifyDataSetChanged()
        notifyDataSetChanged()
    }
}
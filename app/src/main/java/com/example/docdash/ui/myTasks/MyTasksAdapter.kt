package com.example.docdash.ui.myTasks

import com.example.docdash.ui.taskPool.TaskPoolInterface


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.ui.taskPool.ViewHolder
import com.example.docdash.utils.DateTimeHandler

class MyTaskViewHolder(view: View, recyclerViewInterface: MyTasksInterface, type: MyTaskType) : RecyclerView.ViewHolder(view) {
    val dueDate: TextView = view.findViewById(R.id.taskDue)
    val taskDescription: TextView = view.findViewById(R.id.taskDescription)
    val testDescription: TextView = view.findViewById(R.id.testDescription)
    val patient: TextView = view.findViewById(R.id.patient)
    val room: TextView = view.findViewById(R.id.room)

    init {
        view.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                recyclerViewInterface.onClick(adapterPosition, type)
            }
        }
    }
}

class MyTasksAdapter(
    private var taskList: List<TaskGetResponse>,
    recyclerViewInterface: MyTasksInterface,
    private val type: MyTaskType
) : RecyclerView.Adapter<MyTaskViewHolder>() {

    private val taskPoolInterface: MyTasksInterface = recyclerViewInterface

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_pool_item, parent, false)
        return MyTaskViewHolder(view, taskPoolInterface, type)
    }

    override fun onBindViewHolder(holder: MyTaskViewHolder, position: Int) {
        // Bind data to views
        val taskItem = taskList[position]
        holder.dueDate.text = DateTimeHandler.epochSecondsToDateTime(taskItem.deadline?:0 )
        holder.taskDescription.text = taskItem.information ?: "N/A"
        val testText: String = if (taskItem.tests?.isNotEmpty() == true) {
            taskItem.tests!!.joinToString(separator = "\n") { it.information ?: "N/A" }
        } else {
            "N/A"
        }
        holder.testDescription.text = testText
        holder.patient.text = taskItem.patient?.name ?: "N/A"
        holder.room.text = taskItem.entry?.room ?: "N/A"
    }

    fun updateTaskList(newTaskList: List<TaskGetResponse>) {
        this.taskList = newTaskList
        notifyDataSetChanged()
    }
}
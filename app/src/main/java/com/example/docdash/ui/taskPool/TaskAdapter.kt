package com.example.docdash.ui.taskPool

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.utils.DateTimeHandler
import com.example.docdash.utils.StringHelper

class ViewHolder(view: View, recyclerViewInterface: TaskPoolInterface) : RecyclerView.ViewHolder(view) {
    val dueDate: TextView = view.findViewById(R.id.taskDue)
    val taskDescription: TextView = view.findViewById(R.id.taskDescription)
    val testDescription: TextView = view.findViewById(R.id.testDescription)
    val patient: TextView = view.findViewById(R.id.patient)
    val room: TextView = view.findViewById(R.id.room)

    init {
        view.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                recyclerViewInterface.onClickTask(adapterPosition)
            }
        }
    }
}

class TaskAdapter(
    private var taskList: List<TaskGetResponse>,
    recyclerViewInterface: TaskPoolInterface
) : RecyclerView.Adapter<ViewHolder>() {

    private val taskPoolInterface: TaskPoolInterface = recyclerViewInterface

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_pool_item, parent, false)
        return ViewHolder(view, taskPoolInterface)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to views
        val taskItem = taskList[position]
        holder.dueDate.text = DateTimeHandler.epochSecondsToDateTime(taskItem.deadline?:0 )
        holder.taskDescription.text = taskItem.information ?: "N/A"
        val testText: String = StringHelper.buildTestsList(taskItem.tests)
        holder.testDescription.text = testText
        holder.patient.text = taskItem.patient?.name ?: "N/A"
        holder.room.text = taskItem.entry?.room ?: "N/A"
    }

    fun updateTaskList(newTaskList: List<TaskGetResponse>) {
        this.taskList = newTaskList
        notifyDataSetChanged()
    }
}

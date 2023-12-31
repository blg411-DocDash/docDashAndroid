package com.example.docdash.ui.taskPool

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.docdash.R
import com.example.docdash.data.TaskListItem

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
    private var taskList: List<TaskListItem>,
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to views
        holder.dueDate.text = taskList[position].taskDeadline
        holder.taskDescription.text = taskList[position].taskDescription
        holder.testDescription.text = taskList[position].testDescription
        holder.patient.text = taskList[position].patient
        holder.room.text = taskList[position].room
    }

    fun updateTaskList(newTaskList: List<TaskListItem>) {
        this.taskList = newTaskList
        // TODO Check alternatives to notifyDataSetChanged()
        notifyDataSetChanged()
    }
}

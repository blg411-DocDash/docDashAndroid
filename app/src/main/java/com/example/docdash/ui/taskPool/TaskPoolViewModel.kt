package com.example.docdash.ui.taskPool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.docdash.data.TaskItem
import com.example.docdash.dummyData.DummyTaskPool

class TaskPoolViewModel : ViewModel() {
    private val taskList = MutableLiveData<List<TaskItem>>()

    fun updateTaskList() {
        // TODO: Implement when endpoint is ready
        taskList.postValue(DummyTaskPool.dummyPool)
    }

    fun getTaskList(): LiveData<List<TaskItem>> {
        return taskList
    }
}
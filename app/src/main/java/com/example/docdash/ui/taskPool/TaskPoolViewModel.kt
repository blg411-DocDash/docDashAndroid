package com.example.docdash.ui.taskPool

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.TaskListItem
import com.example.docdash.services.BackendAPI
import com.example.docdash.utils.DateTimeHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskPoolViewModel : ViewModel() {
    val taskList = MutableLiveData<List<TaskListItem>>()
    val errorMessage = MutableLiveData<String>()

    fun updateTaskList() {
        viewModelScope.launch(Dispatchers.IO) { makeTaskPoolRequest() }
    }

    private suspend fun makeTaskPoolRequest() {
        try {
            val request = BackendAPI.backendAPI.getAvailableTasks(status = "open", nurse = null)
            if (request.body()?.code == 0) {
                var responseItems = listOf<TaskListItem>()
                val requestData = request.body()?.data!!
                requestData.sortBy { it.deadline }
                for (item in requestData) {
                    responseItems = responseItems.plus(
                        TaskListItem(
                            item.id ?: "",
                            item.information ?: "",
                            DateTimeHandler.epochSecondsToDateTime(item.deadline ?: 0.0),
                            item.status ?: "",
                            "-not implemented-",
                            "-not implemented-",
                            "-not implemented-",
                        )
                    )
                }
                taskList.postValue(responseItems)
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            Log.d("XXX", e.toString())
            errorMessage.postValue("Failed to get tasks due to network error.")
        }
    }
}
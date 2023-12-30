package com.example.docdash.ui.taskPool

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docdash.data.TaskListItem
import com.example.docdash.services.BackendAPI
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
            val response = request.body()
            Log.d("xxx", request.toString())
            if (request.isSuccessful) {
                var responseItems = listOf<TaskListItem>()
                for (item in response!!) {
                    responseItems += TaskListItem(
                        item.id ?: "",
                        item.information ?: "",
                        item.deadline.toString() ?: "",
                        item.status ?: "",
                        "",
                        "ep",
                        ""
                    )
                }
                taskList.postValue(responseItems)
            } else {
                errorMessage.postValue("Failed to get tasks due to invalid credentials.")
            }
        } catch (e: Exception) {
            errorMessage.postValue("Failed to get tasks due to network error.")
        } finally {
            return
        }
    }
}
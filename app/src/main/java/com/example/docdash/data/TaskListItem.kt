package com.example.docdash.data

data class TaskListItem(
    var taskID: Int?,
    var taskDescription: String?,
    var taskDeadline: String?,
    var taskStatus: String?,
    var testDescription: String?,
    var patient: String?,
    var room: String?,
)
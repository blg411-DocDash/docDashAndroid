package com.example.docdash.data

data class TaskListItem(
    // Will be passed directly to the TaskDetails
    var taskID: String?,
    var taskDescription: String?,
    var taskDeadline: String?,
    var taskStatus: String?,
    var testDescription: String?,
    var patient: String?,
    var room: String?,
    // These might be necessary, for displaying the details
    var entryID: String?,
    var tckn: String?,
    // Not sure about this one, probably will change with the API
    var tests: List<TestItem>?
)

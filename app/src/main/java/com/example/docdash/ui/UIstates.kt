package com.example.docdash.ui

import kotlinx.coroutines.sync.Mutex

object UIstates {
    // Mutex is used to lock the thread when the data is being updated
    val availableTasksMutex: Mutex = Mutex()
    val activeTasksMutex: Mutex = Mutex()
    val completedTasksMutex: Mutex = Mutex()
    // State Flags
    var isAvailableTasksValid = false
    var isActiveTasksValid = false
    var isCompletedTasksValid = false
    var isAvailablePatientTestsValid = false

    // Reset the state flags
    fun reset() {
        isAvailableTasksValid = false
        isActiveTasksValid = false
        isCompletedTasksValid = false
        isAvailablePatientTestsValid = false
    }
}
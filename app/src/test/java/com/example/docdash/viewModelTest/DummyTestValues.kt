package com.example.docdash.viewModelTest

import com.example.docdash.data.serviceData.response.EntryGetResponse
import com.example.docdash.data.serviceData.response.PatientGetResponse
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.data.serviceData.response.TestGetResponse

data class DummyTestValues(
    val test : TestGetResponse = TestGetResponse(
        "test", "test", "test", 0, "test", "open",
        "test", 0, "test", "test"
    ),

    val patient: PatientGetResponse = PatientGetResponse(
        "test", 0, 170, 70, "test", 0, 0, "test", "test"
    ),

    val entry: EntryGetResponse = EntryGetResponse(
        "test", "test", "test", 0, "test", 0, "test", 0, "test", 0, 0, "test", "test"
    ),

    val task: TaskGetResponse = TaskGetResponse(
    0, "test", "open", "test", 0, "test", "test", 0, "test", "test", listOf(test), patient, entry
    )
)

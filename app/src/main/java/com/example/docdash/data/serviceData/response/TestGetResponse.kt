package com.example.docdash.data.serviceData.response

data class TestGetResponse(
    // This class holds the response of the test data.
    val task_id: String?,
    val information: String?,
    val created_at: Long?,
    val created_by: String?,
    val status: String?,
    val result: String?,
    val updated_at : Long?,
    val updated_by: String?,
    val id: String?
)
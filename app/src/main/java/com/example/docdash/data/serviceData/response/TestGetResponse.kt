package com.example.docdash.data.serviceData.response

data class TestGetResponse(
    // This class holds the response of the test data.
    var task_id: String?,
    var name: String?,
    var information: String?,
    var created_at: Long?,
    var created_by: String?,
    var status: String?,
    var result: String?,
    var updated_at : Long?,
    var updated_by: String?,
    var id: String?
)
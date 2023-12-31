package com.example.docdash.data.serviceData.response

data class TaskGetResponse(
    var created_at: Int?,
    var information: String?,
    var status : String?,
    var created_by : String?,
    var deadline: Double?,
    var entry_id: String?,
    var id : String?,
    var tests : List<TaskTestResponse>?
)

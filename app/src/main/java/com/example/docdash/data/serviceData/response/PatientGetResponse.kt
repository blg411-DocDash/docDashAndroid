package com.example.docdash.data.serviceData.response

data class PatientGetResponse(
    var name: String?,
    var dob: Double?,
    var height: Int?,
    var weight: Int?,
    var created_by: String?,
    var created_at: Int?,
    var entries: List<EntryGetResponse>?
)

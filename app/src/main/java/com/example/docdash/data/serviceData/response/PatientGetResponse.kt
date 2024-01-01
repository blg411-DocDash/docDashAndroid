package com.example.docdash.data.serviceData.response

data class PatientGetResponse(
    var name: String?,
    var dob: Long?,
    var height: Int?,
    var weight: Int?,
    var created_by: String?,
    var created_at: Long?,
    var updated_at: Long?,
    var updated_by: String?,
    var tckn: String?,
)
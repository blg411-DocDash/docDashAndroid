package com.example.docdash.data.serviceData.response

import java.nio.file.attribute.AclEntryType

data class TaskGetResponse(
    var created_at: Long?,
    var information: String?,
    var status: String?,
    var created_by: String?,
    var deadline: Long?,
    var entry_id: String?,
    var nurse_email: String?,
    var updated_at: Long?,
    var updated_by: String?,
    var id: String?,
    var tests: List<TestGetResponse>?,
    var patient: PatientGetResponse?,
    var entry: EntryGetResponse?
)
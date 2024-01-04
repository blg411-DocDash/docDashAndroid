package com.example.docdash.data.serviceData.requests

import com.example.docdash.services.ApiConstants

data class TaskUpdateRequest(
    var id: String?,
    var status: String?,
    val nurse_email: String = ApiConstants.EMAIL,
)

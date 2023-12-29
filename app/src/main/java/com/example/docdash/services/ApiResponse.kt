package com.example.docdash.services

data class ApiResponse<T>(
    var code: Int?,
    var message: String?,
    var data: T?,
)

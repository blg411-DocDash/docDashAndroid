package com.example.docdash.services

import com.example.docdash.data.LoginRequest
import com.example.docdash.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface BackendService {
    @POST("authenticate")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<ApiResponse<LoginResponse>>
}
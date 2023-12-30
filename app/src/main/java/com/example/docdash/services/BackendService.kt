package com.example.docdash.services

import com.example.docdash.data.serviceData.requests.LoginRequest
import com.example.docdash.data.serviceData.response.EntryGetResponse
import com.example.docdash.data.serviceData.response.LoginResponse
import com.example.docdash.data.serviceData.response.PatientGetResponse
import com.example.docdash.data.serviceData.response.TasksGetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface BackendService {

    @POST("authenticate")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<ApiResponse<LoginResponse>>

    @GET("tasks")
    suspend fun getAvailableTasks(
        @Query("status") status : String?,
        @Query("nurse") nurse : String?,
        @Header ("Authorization") bearerToken : String? = ApiConstants.TOKEN
    ): Response<ApiResponse<Array<TasksGetResponse>>>

    @GET("entries")
    suspend fun getPatientEntry(
        @Query("entry_id") entryID : String?,
        @Header ("Authorization") bearerToken : String? = ApiConstants.TOKEN
    ): Response<ApiResponse<Array<EntryGetResponse>>>

    @GET("patients")
    suspend fun getPatient(
        @Query("tckn") tckn : String?,
        @Header ("Authorization") bearerToken : String? = ApiConstants.TOKEN
    ): Response<ApiResponse<PatientGetResponse>>
}
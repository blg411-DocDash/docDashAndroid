package com.example.docdash.services

object ExceptionMessages {
    private val exception = mapOf(
        1000 to "Interval server exception",
        1001 to "Unsupported HTTP method",
        1002 to "Invalid ObjectId",
        2000 to "Not authenticated",
        2001 to "Not authorized",
        2002 to "Token expired",
        2003 to "Invalid token",
        3000 to  "User not found",
        3001 to "User with email already exists",
        3002 to "User email must be in the request body",
        3003 to "Cannot change created_at or created_by",
        3004 to "email, password, role and name must be given",
        3005 to "role must be one of the followings: admin, doctor, nurse",
        3006 to "given email is not in correct format",
        3007 to "nurse has active tasks",
        4000 to "Patient not found",
        4001 to "Patient with TCKN already exists",
        4002 to "Patient TCKN, name, dob, height, weight must be in the request body",
        4003 to "Invalid keys",
        4004 to "Patient TCKN must be in the request body",
        4005 to "Patient dob must be in format '1923-10-29'",
        4006 to "Patient has no entry",
        4007 to "Patient has active entries",
        5000 to "Entry not found",
        5001 to "Entry with id already exists",
        5002 to "tckn, info, admitted_for, admission_date, room must be in the request body",
        5003 to "Invalid keys",
        5004 to "Entry id must be in the request body",
        5005 to "Admission date must be in format '1923-10-29'",
        5006 to "Body can't contain both 'id' and 'active'",
        5007 to "entry has active tasks",
        6000 to "Task not found",
        6001 to "Task with id already exists",
        6002 to "information, deadline, entry_id must be in the request body",
        6003 to "Deadline must be in format '1923-10-29 10:00'",
        6004 to "for get by id give 'id' or for filter give ('status','nurse_email', 'page', 'limit')",
        6005 to "status must be one of open, in progress, closed or *",
        6006 to "id must given",
        6007 to "Cannot change created_at or created_by",
        6008 to "page & limit must be numeric",
        6009 to "task has active tests",
        7000 to "Test not found",
        7001 to "Test with id already exists",
        7002 to "name, information, task_id must be in the request body",
        7003 to "exactly one of ('id', 'tckn', 'predefined') must be given",
        7004 to "id must be in the request body",
        7005 to "Cannot change created_at or created_by",
        7006 to "Invalid keys given only give one of ('id','tckn','predefined')",
    )
    fun getExceptionMessage(exceptionCode : Int?) : String
    {
        return when (exceptionCode) {
            null -> "An error occurred!"
            else -> exception.getOrDefault(exceptionCode, "An error occurred!")
        }
    }
}
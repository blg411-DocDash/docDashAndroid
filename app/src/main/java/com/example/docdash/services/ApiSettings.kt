package com.example.docdash.services

import android.content.Context

class ApiSettings(val context: Context) {

    private val sharedPref = context.getSharedPreferences("ApiSettings", Context.MODE_PRIVATE)

    fun initLimit () {
        val limit = sharedPref.getInt("limit", 10)
        ApiConstants.LIST_LIMIT = limit
    }

    fun getLimit (): Int {
        return sharedPref.getInt("limit", 10)
    }

    fun saveLimit (limit: Int) {
        with (sharedPref.edit()) {
            putInt("limit", limit)
            apply()
        }
        ApiConstants.LIST_LIMIT = limit
    }

    fun getToken (): String {
        return sharedPref.getString("token", "") ?: ""
    }

    fun saveToken (token: String) {
        with (sharedPref.edit()) {
            putString("token", token)
            apply()
        }
    }
}
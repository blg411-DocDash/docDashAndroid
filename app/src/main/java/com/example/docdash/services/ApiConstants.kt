package com.example.docdash.services

object ApiConstants {
    const val BASE_URL = "https://zfri5sujn7.execute-api.eu-central-1.amazonaws.com/"
    var TOKEN: String = ""
    var ROLE: String = ""
    var NAME: String = ""
    var EMAIL: String = ""

    fun reset() {
        TOKEN = ""
        ROLE = ""
        NAME = ""
    }

    fun isLoggedIn(): Boolean {
        return TOKEN.isNotEmpty()
    }
}



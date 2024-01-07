package com.example.docdash.utils
import com.example.docdash.data.serviceData.response.TestGetResponse

class StringHelper {
    companion object {
        fun buildTestsList(tests: List<TestGetResponse>?): String {
            if (tests.isNullOrEmpty()) {
                return "N/A"
            }
            return tests.joinToString(separator = "\n") {
                when (it.name) {
                    null, "N/A" -> "N/A"
                    "" -> "N/A"
                    else -> it.name!!
                }
            }
        }
    }
}
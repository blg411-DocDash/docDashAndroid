package com.example.docdash.utilityTest

import com.example.docdash.data.serviceData.response.TestGetResponse
import com.example.docdash.utils.DateTimeHandler.Companion.epochSecondsToDateTime
import com.example.docdash.utils.StringHelper
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun testEpochSecondsToDateTime(){
        val test1 = 1704483394L
        val dateTime1 = epochSecondsToDateTime(test1)
        val expected1 = "2024-01-05 22:36:34"
        assertEquals(expected1, dateTime1)

        val test2 = 1704483394.0
        val dateTime2 = epochSecondsToDateTime(test2.toLong())
        assertEquals(expected1, dateTime2)
    }

    @Test
    fun testBuildTestsList(){
        val test1 = emptyList<TestGetResponse>()
        val expected1 = "N/A"
        assertEquals("Empty list failed.", expected1, StringHelper.buildTestsList(test1))

        val nullTest = TestGetResponse(null,
            null, null, null, null, null,
            null, null, null, null)
        val emptyTest = TestGetResponse("",
            "", "", 0, "", "",
            "", 0, "", "")

        val test2 = listOf(emptyTest)
        val expected2 = "N/A"
        assertEquals("Empty test failed.",expected2, StringHelper.buildTestsList(test2))

        val test3 = listOf(nullTest)
        val expected3 = "N/A"
        assertEquals("Test with null fields failed!", expected3, StringHelper.buildTestsList(test3))

        val test4 = null
        val expected4 = "N/A"
        assertEquals("Null test failed!", expected4, StringHelper.buildTestsList(test4))
    }
}
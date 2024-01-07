package com.example.docdash.utilityTest

import com.example.docdash.services.ExceptionMessages
import org.junit.Assert.assertEquals
import org.junit.Test

class ExceptionMessagesTest {

    @Test
    fun `getExceptionMessage with valid exception code should return corresponding message`() {
        // Arrange
        val exceptionCode = 1001

        // Act
        val result = ExceptionMessages.getExceptionMessage(exceptionCode)

        // Assert
        assertEquals("Unsupported HTTP method", result)
    }

    @Test
    fun `getExceptionMessage with null exception code should return default message`() {
        // Arrange
        val exceptionCode: Int? = null

        // Act
        val result = ExceptionMessages.getExceptionMessage(exceptionCode)

        // Assert
        assertEquals("An error occurred!", result)
    }

    @Test
    fun `getExceptionMessage with unknown exception code should return default message`() {
        // Arrange
        val exceptionCode = 9999

        // Act
        val result = ExceptionMessages.getExceptionMessage(exceptionCode)

        // Assert
        assertEquals("An error occurred!", result)
    }
}

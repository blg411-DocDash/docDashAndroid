package com.example.docdash.viewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.ApiResponse
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import com.example.docdash.ui.taskPool.TaskPoolViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TaskPoolViewModelTest {

    // Set the main dispatcher to a TestCoroutineDispatcher for testing coroutines
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: TaskPoolViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        mockkObject(UIstates)
        mockkObject(BackendAPI)

        UIstates.isAvailableTasksValid = true

        // Set up the ViewModel with mocked dependencies
        viewModel = TaskPoolViewModel()
    }

    @After
    fun tearDown() {
        // Cleanup
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
        unmockkAll()
    }

    @Test
    fun `Backend Requests Returns Empty List`() = runBlockingTest {
        // Arrange
        val taskList = emptyList<TaskGetResponse>()
        val taskListRequest = emptyArray<TaskGetResponse>()
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskListRequest

        // Act
        viewModel.updateTaskList()

        // Assert
        assert(viewModel.taskList.value == taskList)
    }

    @Test
    fun `Backend Requests Returns Null List`() = runBlockingTest {
        // Arrange
        val taskList = null
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskList

        // Act
        viewModel.updateTaskList()

        // Assert
        assert(viewModel.taskList.value == taskList)
    }

    @Test
    fun `Backend Requests Returns Null Body`() = runBlockingTest {
        // Arrange
        val taskList = null
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns null

        // Act
        viewModel.updateTaskList()

        // Assert
        assert(viewModel.taskList.value == taskList)
    }

    @Test
    fun `Backend Requests Returns Normal Response`() = runBlockingTest {
        // Arrange
        val taskList = listOf(
            TaskGetResponse(
                null, null, null, null, null, null, null, null, null, null, null, null, null
            )
        )
        val taskListRequest = arrayOf(
            TaskGetResponse(
                null, null, null, null, null, null, null, null, null, null, null, null, null
            )
        )
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskListRequest

        // Act
        viewModel.updateTaskList()

        // Assert
        assert(viewModel.taskList.value == taskList)
    }

    @Test
    fun `Backend Requests Returns Error Code`() = runBlockingTest {
        // Arrange
        val taskList = viewModel.taskList.value
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 1001

        // Act
        viewModel.updateTaskList()

        // Assert
        assert(viewModel.taskList.value == taskList)
    }
}



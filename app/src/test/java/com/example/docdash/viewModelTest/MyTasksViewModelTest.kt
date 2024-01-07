package com.example.docdash.viewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.docdash.data.serviceData.response.TaskGetResponse
import com.example.docdash.services.ApiResponse
import com.example.docdash.services.BackendAPI
import com.example.docdash.ui.UIstates
import com.example.docdash.ui.myTasks.MyTasksViewModel
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
class MyTasksViewModelTest {

    // Set the main dispatcher to a TestCoroutineDispatcher for testing coroutines
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: MyTasksViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        mockkObject(UIstates)
        mockkObject(BackendAPI)

        UIstates.isAvailableTasksValid = true

        // Set up the ViewModel with mocked dependencies
        viewModel = MyTasksViewModel()
    }

    @After
    fun tearDown() {
        // Cleanup
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
        unmockkAll()
    }

    @Test
    fun myTasksEmptyListTest() = runBlockingTest {
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
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskList)

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskList)
    }

    @Test
    fun myTasksNullListTest() = runBlockingTest {
        // Arrange
        val taskList = null
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskList

        // Act
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskList)

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskList)
    }

    @Test
    fun myTasksActiveEmptyListTest() = runBlockingTest {
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
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskList)
    }

    @Test
    fun myTasksActiveNullListTest() = runBlockingTest {
        // Arrange
        val taskList = null
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskList

        // Act
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskList)
    }

    @Test
    fun myTasksActiveNullRequestBodyTest() = runBlockingTest {
        // Arrange
        val activeTasksList = viewModel.activeTasks.value
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns null

        // Act
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == activeTasksList)
    }

    @Test
    fun myTasksActiveNormalResponseTest() = runBlockingTest {
        // Arrange
        val dummyTask = DummyTestValues().task

        val taskList = listOf(dummyTask)
        val taskListRequest = arrayOf(dummyTask)
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskListRequest

        // Act
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskList)
    }

    @Test
    fun myTasksActiveApiErrorTest() = runBlockingTest {
        // Arrange
        val taskListActive = viewModel.activeTasks.value
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 1001

        // Act
        viewModel.updateActiveTasks()

        // Assert
        assert(viewModel.activeTasks.value == taskListActive)
    }

    @Test
    fun myTasksCompletedEmptyListTest() = runBlockingTest {
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
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskList)
    }

    @Test
    fun myTasksCompletedNullListTest() = runBlockingTest {
        // Arrange
        val taskList = null
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskList

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskList)
    }

    @Test
    fun myTasksCompletedNullRequestBodyTest() = runBlockingTest {
        // Arrange
        val completedTasksList = viewModel.completedTasks.value
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns null

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == completedTasksList)
    }

    @Test
    fun myTasksCompletedNormalResponseTest() = runBlockingTest {
        // Arrange
        val dummyTask = DummyTestValues().task

        val taskList = listOf(dummyTask)
        val taskListRequest = arrayOf(dummyTask)
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body() } returns mockk()
        every { request.body()?.code } returns 0
        every { request.body()?.data } returns taskListRequest

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskList)
    }

    @Test
    fun myTasksCompletedApiErrorTest() = runBlockingTest {
        // Arrange
        val taskListCompleted = viewModel.completedTasks.value
        val request = mockk<retrofit2.Response<ApiResponse<Array<TaskGetResponse>>>>()

        // Mock the backend request
        coEvery { BackendAPI.backendAPI.getTasks(any(), any(), any()) } returns request
        every { request.body()?.code } returns 1001

        // Act
        viewModel.updateCompletedTasks()

        // Assert
        assert(viewModel.completedTasks.value == taskListCompleted)
    }
}



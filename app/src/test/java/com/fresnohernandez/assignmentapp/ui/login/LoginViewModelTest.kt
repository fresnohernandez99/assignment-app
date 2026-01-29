package com.fresnohernandez.assignmentapp.ui.login

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() = runTest {
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("", state.emailInput)
        assertEquals("", state.passwordInput)
    }

    @Test
    fun `updateState updates email and password`() = runTest {
        viewModel.updateState(email = "test@example.com", password = "password123")
        
        val state = viewModel.uiState.value
        assertEquals("test@example.com", state.emailInput)
        assertEquals("password123", state.passwordInput)
    }

    @Test
    fun `login flow updates loading state and calls onSuccess`() = runTest {
        var successCalled = false
        
        viewModel.uiState.test {
            // Initial state
            assertEquals(LoginUiState(), awaitItem())

            viewModel.login {
                successCalled = true
            }

            // Loading starts
            assertTrue(awaitItem().isLoading)

            // Skip delay in login
            testDispatcher.scheduler.advanceTimeBy(1501)

            // Loading ends
            assertFalse(awaitItem().isLoading)
            assertTrue(successCalled)
        }
    }
}

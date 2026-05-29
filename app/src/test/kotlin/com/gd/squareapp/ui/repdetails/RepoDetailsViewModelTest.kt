package com.gd.squareapp.ui.repdetails

import app.cash.turbine.test
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.RepoOwner
import com.gd.domain.usecase.GetRepoDetailsUseCase
import com.gd.squareapp.R
import com.gd.squareapp.ui.repodetails.RepoDetailsViewModel
import com.gd.squareapp.ui.repodetails.event.RepoDetailsEvent
import com.gd.squareapp.ui.repodetails.state.RepoDetailsUiState
import com.gd.squareapp.ui.repolist.state.ToolbarUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val TEST_REPO_NAME = "testRepoName"
private const val TEST_ERROR_MESSAGE = "test error message"

class RepoDetailsViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val getRepoDetails: GetRepoDetailsUseCase = mockk()

    private lateinit var viewModel: RepoDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RepoDetailsViewModel(getRepoDetails, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given use case return success when view model invoked then verify ui state`() = runTest {

        val expected = RepoDetailsUiState(
            repo = testRepo,
            toolbarUiState = ToolbarUiState(title = R.string.detail_title, isBackVisible = true)
        )
        // Given
        coEvery { getRepoDetails(TEST_REPO_NAME) } returns RepoDetailResult.Data(testRepo)
        // When
        viewModel.fetchRepoDetails(TEST_REPO_NAME)

        advanceUntilIdle()

        val result = viewModel.repoDetailsUiState
        // Then
        assertEquals(expected, result.value)
    }

    @Test
    fun `Given use case return error when view model invoked then verify ui state`() = runTest {
        val expected = RepoDetailsUiState(
            error = TEST_ERROR_MESSAGE,
            toolbarUiState = ToolbarUiState(title = R.string.detail_title, isBackVisible = true),
        )
        // Given
        coEvery { getRepoDetails(TEST_REPO_NAME) } returns RepoDetailResult.Error(TEST_ERROR_MESSAGE)
        // When
        viewModel.fetchRepoDetails(TEST_REPO_NAME)
        advanceUntilIdle()
        // Then
        assertEquals(expected, viewModel.repoDetailsUiState.value)
    }

    @Test
    fun `Given view model when back event is triggered then verify event`() = runTest {
        viewModel.event.test {
            viewModel.onRepoDetailsEvent(RepoDetailsEvent.OnBackClick)

            assertEquals(RepoDetailsEvent.OnBackClick, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }


    companion object {
        val testRepo = Repo(
            id = "1234", "testString", false, "testDescription", "testUrl", 10,
            RepoOwner(6754, "testName", "testUrl", "fdr")
        )
    }
}

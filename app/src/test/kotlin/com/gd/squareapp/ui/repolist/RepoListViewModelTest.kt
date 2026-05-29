package com.gd.squareapp.ui.repolist

import app.cash.turbine.test
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.ReposResult
import com.gd.domain.usecase.GetSquareProjectListUseCase
import com.gd.squareapp.ui.repolist.event.RepoListEvent
import com.gd.squareapp.ui.repolist.state.RepoListUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val getSquareProjects: GetSquareProjectListUseCase = mockk()

    private lateinit var viewModel: RepoListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given use case return success when view model invoked then verify ui state`() = runTest {
        val expected = RepoListUiState(repos = repoList)
        // Given
        coEvery { getSquareProjects() } returns ReposResult.Data(repoList)

        // When
        viewModel = RepoListViewModel(getSquareProjects, testDispatcher)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        assertEquals(expected, viewModel.repoListUiState.value)
    }

    @Test
    fun `Given use case return error when view model invoked then verify ui state`() = runTest {
        val expected = RepoListUiState(error = "test error message")
        // Given
        coEvery { getSquareProjects() } returns ReposResult.Error("test error message")
        // When
        viewModel = RepoListViewModel(getSquareProjects, testDispatcher)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        assertEquals(expected, viewModel.repoListUiState.value)
    }

    @Test
    fun `Given view model when back event is triggered then verify event`() = runTest {
        // Given
        coEvery { getSquareProjects() } returns ReposResult.Data(repoList)
        viewModel = RepoListViewModel(getSquareProjects, testDispatcher)

        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.event.test {
            viewModel.onRepoListEvent(RepoListEvent.OnBackClick)

            assertEquals(RepoListEvent.OnBackClick, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        val repoList = listOf(
            Repo(
                id = "1234", "testString", false, "testDescription", "testUrl", 10,
                RepoOwner(6754, "testName", "testUrl", "fdr")
            )
        )
    }
}

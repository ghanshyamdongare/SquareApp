package com.gd.data.repository

import com.gd.data.api.RepoDetailApiService
import com.gd.data.api.RepoListApiService
import com.gd.data.datasource.RepoDataItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val TEST_REPO_NAME = "testRepoName"
private const val ERROR_MESSAGE = "Error message"

class SquareRepositoryTest {
    private val repoListApiService: RepoListApiService = mockk()
    private val repoDetailsApiService: RepoDetailApiService = mockk()

    private lateinit var repository: SquareRepository

    @Before
    fun setUp() {
        repository = SquareRepository(repoListApiService, repoDetailsApiService)
    }

    @Test
    fun `Given repoListApiService returns success, when repository is invoked, then return success`() =
        runTest {
            val expected = listOf(RepoDataItem())

            // Given
            coEvery { repoListApiService.getSquareRepos() } returns Result.success(expected)
            // When
            val result = repository.getSquareRepos()
            // Then
            assertEquals(Result.success(expected), result)
        }

    @Test
    fun `Given repoListApiService returns error, when repository is invoked, then return error`() =
        runTest {
            // Given
            val throwable: Throwable = mockk()
            every { throwable.message } returns ERROR_MESSAGE
            coEvery { repoListApiService.getSquareRepos() } returns Result.failure(throwable)
            // When
            val result = repository.getSquareRepos()
            // Then
            assertEquals(throwable, result.exceptionOrNull())
        }

    @Test
    fun `Given repoDetailsApiService returns success, when repository is invoked, then return success`() =
        runTest {
            // Given
            val repoName = TEST_REPO_NAME
            val repoDataItem = RepoDataItem()
            val expected = Result.success(repoDataItem)
            coEvery { repoDetailsApiService.getRepoDetails(repoName) } returns Result.success(
                repoDataItem
            )
            // When
            val result = repository.getRepoDetails(repoName)
            // Then
            assertEquals(expected, result)
        }

    @Test
    fun `Given repoDetailsApiService returns error, when repository is invoked, then return error`() =
        runTest {
            // Given
            val repoName = TEST_REPO_NAME
            val throwable: Throwable = mockk()
            every { throwable.message } returns ERROR_MESSAGE
            coEvery { repoDetailsApiService.getRepoDetails(repoName) } returns Result.failure(
                throwable
            )
            // When
            val result = repository.getRepoDetails(repoName)
            // Then
            assertEquals(throwable, result.exceptionOrNull())
        }

}

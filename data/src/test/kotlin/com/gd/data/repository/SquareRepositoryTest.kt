package com.gd.data.repository

import com.gd.data.api.RepoListApiService
import com.gd.data.datasource.RepoDataItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SquareRepositoryTest {
    private val apiService: RepoListApiService = mockk()

    private lateinit var repository: SquareRepository

    @Before
    fun setUp() {
        repository = SquareRepository(apiService)
    }

    @Test
    fun `Given api service returns success, when repository is invoked, then return success`() =
        runTest {
            // Given
            coEvery { apiService.getSquareRepos() } returns Result.success(repoList)
            // When
            val result = repository.getSquareRepos()
            // Then
            assertEquals(Result.success(repoList), result)
        }

    @Test
    fun `Given api service returns error, when repository is invoked, then return error`() =
        runTest {
            // Given
            val throwable: Throwable = mockk()
            every { throwable.message } returns "Error message"
            coEvery { apiService.getSquareRepos() } returns Result.failure(throwable)
            // When
            val result = repository.getSquareRepos()
            // Then
            assertEquals(throwable, result.exceptionOrNull())
        }

    companion object {
        val repoList = listOf(RepoDataItem())
    }
}

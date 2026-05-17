package com.gd.domain.usecase

import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.RepoResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val TEST_ERROR = "test error message"

class GetSquareProjectListUseCaseTest {
    val squareRepoGateway: SquareGateway = mockk()

    private lateinit var useCase: GetSquareProjectListUseCase

    @Before
    fun setUp() {
        useCase = GetSquareProjectListUseCase(squareRepoGateway)
    }

    @Test
    fun `Given gateway returns success, when use case is invoked, then return success`() = runTest {
        // Given
        coEvery { squareRepoGateway.getRepoList() } returns RepoResult.Success(repoList)
        // When
        val result = useCase()
        // Then
        assertEquals(RepoResult.Success(repoList), result)
    }

    @Test
    fun `Given gateway returns error, when use case is invoked, then return error`() = runTest {
        // Given
        coEvery { squareRepoGateway.getRepoList() } returns RepoResult.Error(TEST_ERROR)
        // When
        val result = useCase()
        // Then
        assertEquals(RepoResult.Error(TEST_ERROR), result)
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

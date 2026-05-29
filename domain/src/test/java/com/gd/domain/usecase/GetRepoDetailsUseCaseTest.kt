package com.gd.domain.usecase

import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.RepoOwner
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val TEST_REPO_NAME = "testRepoName"
private const val TEST_ERROR = "test error message"

class GetRepoDetailsUseCaseTest {
    val squareRepoGateway: SquareGateway = mockk()

    private lateinit var useCase: GetRepoDetailsUseCase

    @Before
    fun setUp() {
        useCase = GetRepoDetailsUseCase(squareRepoGateway)
    }

    @Test
    fun `Given gateway returns success, when use case is invoked, then return success`() = runTest {
        // Given
        coEvery { squareRepoGateway.getRepoDetails(TEST_REPO_NAME) } returns RepoDetailResult.Data(
            testRepo
        )
        // When
        val result = useCase(TEST_REPO_NAME)
        // Then
        assertEquals(RepoDetailResult.Data(testRepo), result)
    }

    @Test
    fun `Given gateway returns error, when use case is invoked, then return error`() = runTest {
        // Given
        coEvery { squareRepoGateway.getRepoDetails(TEST_REPO_NAME) } returns RepoDetailResult.Error(
            TEST_ERROR
        )
        // When
        val result = useCase(TEST_REPO_NAME)
        // Then
        assertEquals(RepoDetailResult.Error(TEST_ERROR), result)
    }

    companion object {
        val testRepo = Repo(
            id = "1234", "testString", false, "testDescription", "testUrl", 10,
            RepoOwner(6754, "testName", "testUrl", "fdr")
        )
    }
}

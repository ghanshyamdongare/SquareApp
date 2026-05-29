package com.gd.gateway

import com.gd.data.datasource.Owner
import com.gd.data.datasource.RepoDataItem
import com.gd.data.repository.SquareRepository
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.ReposResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

private const val TEST_REPO_NAME = "test repo name"
private const val TEST_ERROR_MESSAGE = "TEST error message"

class SquareGatewayImplTest {
    private val squareRepository: SquareRepository = mockk()

    private lateinit var squareGateway: SquareGatewayImpl

    @Before
    fun setUp() {
        squareGateway = SquareGatewayImpl(squareRepository)
    }

    @Test
    fun `Given getSquareRepos repository returns success, when gateway is invoked, then return success`() =
        runTest {
            coEvery { squareRepository.getSquareRepos() } returns Result.success(repoDataItemList)

            val result = squareGateway.getRepoList()

            assert(result is ReposResult.Data)
            assertEquals(repoList[0].name, (result as ReposResult.Data).repos[0].name)
        }

    @Test
    fun `Given getSquareRepos repository returns error, when gateway is invoked, then return error`() =
        runTest {
            val throwable: Throwable = mockk()
            every { throwable.message } returns TEST_ERROR_MESSAGE
            coEvery { squareRepository.getSquareRepos() } returns Result.failure(throwable)

            val result = squareGateway.getRepoList()

            assert(result is ReposResult.Error)
            assertEquals(throwable.message, (result as ReposResult.Error).errorMessage)
        }

    @Test
    fun `Given getRepoDetails repository returns success, when gateway is invoked, then return success`() =
        runTest {
            coEvery { squareRepository.getRepoDetails(TEST_REPO_NAME) } returns Result.success(
                testRepo
            )

            val result = squareGateway.getRepoDetails(TEST_REPO_NAME)
            assert(result is RepoDetailResult.Data)
            assertEquals(testRepo.name, (result as RepoDetailResult.Data).repo.name)
        }

    @Test
    fun `Given getRepoDetails repository returns error, when gateway is invoked, then return error`() =
        runTest {
            val throwable: Throwable = mockk()
            every { throwable.message } returns TEST_ERROR_MESSAGE
            coEvery { squareRepository.getRepoDetails(TEST_REPO_NAME) } returns Result.failure(
                throwable
            )
            val result = squareGateway.getRepoDetails(TEST_REPO_NAME)
            assert(result is RepoDetailResult.Error)
            assertEquals(throwable.message, (result as RepoDetailResult.Error).message)
        }

    companion object {
        val testRepo = RepoDataItem(
            id = 1234,
            name = "TestString",
            private = false,
            description = "testDescription",
            gitUrl = "testUrl",
            watchers = 10,
            owner = Owner(id = 6754, login = "testName", avatarUrl = "testUrl", type = "fdr")
        )
        val repoDataItemList = listOf(testRepo)

        val repoList = listOf(
            Repo(
                id = "1234", "TestString", false, "testDescription", "testUrl", 10,
                RepoOwner(6754, "testName", "testUrl", "fdr")
            )
        )
    }
}

package com.gd.gateway

import com.gd.data.datasource.Owner
import com.gd.data.datasource.RepoDataItem
import com.gd.data.repository.SquareRepository
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.RepoResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SquareGatewayImplTest {
    private val squareRepository: SquareRepository = mockk()

    private lateinit var squareGateway: SquareGatewayImpl

    @Before
    fun setUp() {
        squareGateway = SquareGatewayImpl(squareRepository)
    }

    @Test
    fun `Given repository returns success, when gateway is invoked, then return success`() =
        runTest {
            coEvery { squareRepository.getSquareRepos() } returns Result.success(repoDataItemList)

            val result = squareGateway.getRepoList()

            assert(result is RepoResult.Success)
            assertEquals(repoList[0].name, (result as RepoResult.Success).repos[0].name)
        }

    @Test
    fun `Given repository returns error, when gateway is invoked, then return error`() =
        runTest {
            val throwable: Throwable = mockk()
            every { throwable.message } returns "Error message"
            coEvery { squareRepository.getSquareRepos() } returns Result.failure(throwable)

            val result = squareGateway.getRepoList()

            assert(result is RepoResult.Error)
            assertEquals(throwable.message, (result as RepoResult.Error).errorMessage)
        }

    companion object {
        val repoDataItemList = listOf(
            RepoDataItem(
                id = 1234,
                name = "TestString",
                private = false,
                description = "testDescription",
                gitUrl = "testUrl",
                watchers = 10,
                owner = Owner(id = 6754, login = "testName", avatarUrl = "testUrl", type = "fdr")
            )
        )

        val repoList = listOf(
            Repo(
                id = "1234", "TestString", false, "testDescription", "testUrl", 10,
                RepoOwner(6754, "testName", "testUrl", "fdr")
            )
        )
    }
}

package com.gd.gateway

import com.gd.data.repository.SquareRepository
import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.ReposResult
import com.gd.gateway.mapper.toRepoDetailsResultSuccess
import com.gd.gateway.mapper.toRepoResultSuccess
import javax.inject.Inject

private const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

class SquareGatewayImpl @Inject constructor(
    val squareRepository: SquareRepository,
) : SquareGateway {
    override suspend fun getRepoList(): ReposResult =
        squareRepository.getSquareRepos().fold(
            onSuccess = {
                it.toRepoResultSuccess()
            },
            onFailure = {
                ReposResult.Error(it.message ?: DEFAULT_ERROR_MESSAGE)
            }
        )

    override suspend fun getRepoDetails(repoName: String): RepoDetailResult =
        squareRepository.getRepoDetails(repoName).fold(
            onSuccess = { it.toRepoDetailsResultSuccess() },
            onFailure = {
                RepoDetailResult.Error(it.message ?: DEFAULT_ERROR_MESSAGE)
            }
        )
}

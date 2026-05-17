package com.gd.gateway

import com.gd.data.repository.SquareRepository
import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.RepoResult
import com.gd.gateway.mapper.toRepoResultSuccess
import javax.inject.Inject

private const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

class SquareGatewayImpl @Inject constructor(
    val squareRepository: SquareRepository,
) : SquareGateway {
    override suspend fun getRepoList(): RepoResult =
        squareRepository.getSquareRepos().fold(
            onSuccess = {
                it.toRepoResultSuccess()
            },
            onFailure = {
                RepoResult.Error(it.message ?: DEFAULT_ERROR_MESSAGE)
            }
        )
}

package com.gd.domain.usecase

import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.RepoDetailResult
import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(
    val squareRepoGateway: SquareGateway
) {
    suspend operator fun invoke(repoName: String): RepoDetailResult =
        squareRepoGateway.getRepoDetails(repoName)
}

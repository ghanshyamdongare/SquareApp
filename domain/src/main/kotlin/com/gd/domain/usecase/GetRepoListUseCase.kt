package com.gd.domain.usecase

import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.RepoResult
import javax.inject.Inject

class GetSquareProjectListUseCase @Inject constructor(
    val squareRepoGateway: SquareGateway
) {
    suspend operator fun invoke(): RepoResult = squareRepoGateway.getRepoList()
}

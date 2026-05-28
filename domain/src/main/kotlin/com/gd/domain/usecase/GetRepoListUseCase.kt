package com.gd.domain.usecase

import com.gd.domain.gateway.SquareGateway
import com.gd.domain.model.ReposResult
import javax.inject.Inject

class GetSquareProjectListUseCase @Inject constructor(
    val squareRepoGateway: SquareGateway
) {
    suspend operator fun invoke(): ReposResult = squareRepoGateway.getRepoList()
}

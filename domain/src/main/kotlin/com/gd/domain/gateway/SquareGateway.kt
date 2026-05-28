package com.gd.domain.gateway

import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.ReposResult

interface SquareGateway {
    suspend fun getRepoList(): ReposResult
    suspend fun getRepoDetails(repoName: String): RepoDetailResult
}

package com.gd.domain.gateway

import com.gd.domain.model.RepoResult

interface SquareGateway {
    suspend fun getRepoList(): RepoResult
}

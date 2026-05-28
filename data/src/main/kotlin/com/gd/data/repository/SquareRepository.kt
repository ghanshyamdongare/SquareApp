package com.gd.data.repository

import com.gd.data.api.RepoDetailApiService
import com.gd.data.api.RepoListApiService
import com.gd.data.datasource.RepoDataItem
import javax.inject.Inject

class SquareRepository @Inject constructor(
    private val repoListApiService: RepoListApiService,
    private val repoDetailsApiService: RepoDetailApiService,
) {
    suspend fun getSquareRepos(): Result<List<RepoDataItem>> =
        repoListApiService.getSquareRepos()

    suspend fun getRepoDetails(repoName: String): Result<RepoDataItem> =
        repoDetailsApiService.getRepoDetails(repoName)
}

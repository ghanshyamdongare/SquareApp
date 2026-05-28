package com.gd.data.repository

import com.gd.data.api.RepoListApiService
import com.gd.data.datasource.RepoDataItem
import javax.inject.Inject

class SquareRepository @Inject constructor(
    private val repoListApiService: RepoListApiService,
) {
    suspend fun getSquareRepos(): Result<List<RepoDataItem>> =
        repoListApiService.getSquareRepos()
}

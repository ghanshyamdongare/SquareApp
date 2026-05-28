package com.gd.data.api

import com.gd.data.common.Constants
import com.gd.data.datasource.RepoDataItem
import retrofit2.http.GET

interface RepoListApiService {
    @GET(Constants.SQUARE_REPOS_URL)
    suspend fun getSquareRepos(): Result<List<RepoDataItem>>
}

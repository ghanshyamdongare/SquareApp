package com.gd.data.api

import com.gd.data.common.Constants
import com.gd.data.common.Constants.REPO_NAME
import com.gd.data.datasource.RepoDataItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoDetailApiService {
    @GET("${Constants.SQUARE_REPO_DETAILS}{$REPO_NAME}")
    suspend fun getRepoDetails(
        @Path(REPO_NAME) repoName: String,
    ): Result<RepoDataItem>
}

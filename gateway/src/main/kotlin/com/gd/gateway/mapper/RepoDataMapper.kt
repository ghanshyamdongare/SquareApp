package com.gd.gateway.mapper

import com.gd.data.datasource.RepoDataItem
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoResult

fun List<RepoDataItem>.toRepoResultSuccess(): RepoResult.Success = RepoResult.Success(
    repos = this.map { data ->
        Repo(
            id = data.id.toString(),
            name = data.name.orEmpty(),
            isPrivate = data.private ?: true
        )
    }
)

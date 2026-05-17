package com.gd.gateway.mapper

import com.gd.data.datasource.RepoDataItem
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.RepoResult

fun List<RepoDataItem>.toRepoResultSuccess(): RepoResult.Success = RepoResult.Success(
    repos = this.sortedByDescending { it.watchers ?: 0 }
        .map { data ->
            Repo(
                id = data.id.toString(),
                name = data.name.orEmpty().replaceFirstChar { it.uppercase() },
                isPrivate = data.private ?: true,
                description = data.description.orEmpty(),
                gitUrl = data.gitUrl.orEmpty(),
                watchers = data.watchers ?: 0,
                repoOwner = RepoOwner(
                    id = data.owner?.id ?: 0,
                    login = data.owner?.login.orEmpty(),
                    avatarUrl = data.owner?.avatarUrl.orEmpty(),
                    type = data.owner?.type.orEmpty(),
                )
            )
        }
)

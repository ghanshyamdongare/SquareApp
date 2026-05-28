package com.gd.gateway.mapper

import com.gd.data.datasource.RepoDataItem
import com.gd.domain.model.Repo
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.model.RepoOwner
import com.gd.domain.model.ReposResult

fun List<RepoDataItem>.toRepoResultSuccess(): ReposResult.Data = ReposResult.Data(
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

fun RepoDataItem.toRepoDetailsResultSuccess(): RepoDetailResult.Data = RepoDetailResult.Data(
    repo = Repo(
        id = this.id.toString(),
        name = this.name.orEmpty().replaceFirstChar { it.uppercase() },
        isPrivate = this.private ?: true,
        description = this.description.orEmpty(),
        gitUrl = this.gitUrl.orEmpty(),
        watchers = this.watchers ?: 0,
        repoOwner = RepoOwner(
            id = this.owner?.id ?: 0,
            login = this.owner?.login.orEmpty(),
            avatarUrl = this.owner?.avatarUrl.orEmpty(),
            type = this.owner?.type.orEmpty(),
        )
    )
)

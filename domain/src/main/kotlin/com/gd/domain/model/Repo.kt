package com.gd.domain.model

data class Repo(
    val id: String,
    val name: String,
    val isPrivate: Boolean,
    val description: String? = null,
    val gitUrl: String,
    val watchers: Int,
    val repoOwner: RepoOwner,
)

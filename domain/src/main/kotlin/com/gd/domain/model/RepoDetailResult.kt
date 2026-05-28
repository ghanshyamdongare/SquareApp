package com.gd.domain.model

interface RepoDetailResult {
    data class Data(val repo: Repo) : RepoDetailResult
    data class Error(val message: String) : RepoDetailResult
}

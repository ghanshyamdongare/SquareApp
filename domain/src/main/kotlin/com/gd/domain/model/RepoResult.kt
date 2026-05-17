package com.gd.domain.model

sealed interface RepoResult {
    data class Success(val repos: List<Repo>) : RepoResult
    data class Error(val errorMessage: String) : RepoResult
}

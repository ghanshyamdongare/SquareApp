package com.gd.domain.model

sealed interface ReposResult {
    data class Data(val repos: List<Repo>) : ReposResult
    data class Error(val errorMessage: String) : ReposResult
}

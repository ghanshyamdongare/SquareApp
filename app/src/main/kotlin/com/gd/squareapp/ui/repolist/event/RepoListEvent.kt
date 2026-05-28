package com.gd.squareapp.ui.repolist.event

sealed interface RepoListEvent {
    data class OnListItemClick(val repoName: String) : RepoListEvent
    data object OnBackClick : RepoListEvent
}

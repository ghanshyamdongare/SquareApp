package com.gd.squareapp.ui.repodetails.event

sealed interface RepoDetailsEvent {
    data object OnBackClick : RepoDetailsEvent
}

package com.gd.squareapp.ui.repodetails.state

import com.gd.domain.model.Repo
import com.gd.squareapp.ui.repolist.state.ToolbarUiState

data class RepoDetailsUiState(
    val toolbarUiState: ToolbarUiState = ToolbarUiState(),
    val isLoading: Boolean = false,
    val repo: Repo? = null,
    val error: String? = null,
)

package com.gd.squareapp.ui.repolist.state

import androidx.annotation.StringRes
import com.gd.domain.model.Repo
import com.gd.sqaureapp.R

data class RepoListUiState(
    val toolbarUiState: ToolbarUiState = ToolbarUiState(),
    val isLoading: Boolean = false,
    val repos: List<Repo> = emptyList(),
    val error: String? = null,
)

data class ToolbarUiState(
    @StringRes val title: Int = R.string.home_title,
    val isBackVisible: Boolean = false,
)

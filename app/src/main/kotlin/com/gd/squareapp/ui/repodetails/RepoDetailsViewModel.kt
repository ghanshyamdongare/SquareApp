package com.gd.squareapp.ui.repodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gd.domain.model.RepoDetailResult
import com.gd.domain.usecase.GetRepoDetailsUseCase
import com.gd.squareapp.R
import com.gd.squareapp.ui.repodetails.event.RepoDetailsEvent
import com.gd.squareapp.ui.repodetails.state.RepoDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepoDetails: GetRepoDetailsUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _repoDetailsUiState = MutableStateFlow(RepoDetailsUiState())
    val repoDetailsUiState = _repoDetailsUiState.asStateFlow()

    private val _event = MutableSharedFlow<RepoDetailsEvent>()
    val event = _event.asSharedFlow()

    fun fetchRepoDetails(repoName: String) {
        viewModelScope.launch {
            updateToolBarUiState()
            updateLoadingState(true)
            val result = withContext(ioDispatcher) { getRepoDetails(repoName) }
            updateLoadingState(false)
            when (result) {
                is RepoDetailResult.Data -> {
                    _repoDetailsUiState.update {
                        it.copy(repo = result.repo)
                    }
                }

                is RepoDetailResult.Error -> {
                    _repoDetailsUiState.update {
                        it.copy(error = result.message)
                    }
                }
            }
        }
    }

    private fun updateToolBarUiState() {
        _repoDetailsUiState.update {
            it.copy(
                toolbarUiState = it.toolbarUiState.copy(
                    isBackVisible = true,
                    title = R.string.detail_title,
                )
            )
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _repoDetailsUiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun onRepoDetailsEvent(event: RepoDetailsEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}

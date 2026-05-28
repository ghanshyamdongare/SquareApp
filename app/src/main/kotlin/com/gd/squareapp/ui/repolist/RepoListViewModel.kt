package com.gd.squareapp.ui.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gd.domain.model.ReposResult
import com.gd.domain.usecase.GetSquareProjectListUseCase
import com.gd.squareapp.ui.repolist.event.RepoListEvent
import com.gd.squareapp.ui.repolist.state.RepoListUiState
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
class RepoListViewModel @Inject constructor(
    val getSquareProjects: GetSquareProjectListUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _repoListUiState = MutableStateFlow(RepoListUiState())
    val repoListUiState = _repoListUiState.asStateFlow()

    private val _event = MutableSharedFlow<RepoListEvent>()
    val event = _event.asSharedFlow()

    init {
        getProjectList()
    }

    private fun getProjectList() {
        viewModelScope.launch {
            updateLoadingState(true)
            val result = withContext(ioDispatcher) { getSquareProjects() }
            updateLoadingState(false)
            when (result) {
                is ReposResult.Data -> {
                    _repoListUiState.update {
                        it.copy(repos = result.repos)
                    }
                }

                is ReposResult.Error -> {
                    _repoListUiState.update {
                        it.copy(error = result.errorMessage)
                    }
                }
            }
        }
    }

    fun refreshProjectList() {
        getProjectList()
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _repoListUiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    fun onRepoListEvent(event: RepoListEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}

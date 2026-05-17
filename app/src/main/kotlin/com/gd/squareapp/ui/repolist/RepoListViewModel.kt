package com.gd.squareapp.ui.repolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gd.domain.model.RepoResult
import com.gd.domain.usecase.GetSquareProjectListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    val getSquareProjects: GetSquareProjectListUseCase,
) : ViewModel() {
    init {
        Log.d("RepoListViewModel", "RepoListViewModel init")
        getProjectList()
    }

    private fun getProjectList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { getSquareProjects() }
            when (result) {
                is RepoResult.Success -> {
                    // update success result on ui
                    Log.d("Testing view model", "result $result")
                }

                is RepoResult.Error -> {
                    // update error result on ui
                    Log.e("Testing view model", "result $result")
                }
            }
        }
    }
}

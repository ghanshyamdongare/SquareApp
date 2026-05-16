package com.gd.squareapp.ui.repolist

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor() : ViewModel() {
    init {
        Log.d("RepoListViewModel", "RepoListViewModel init")
    }
}

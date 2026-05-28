package com.gd.squareapp.ui.repolist.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.gd.squareapp.R
import com.gd.squareapp.ui.common.screen.CommonDialog
import com.gd.squareapp.ui.common.screen.LoadingView
import com.gd.squareapp.ui.repolist.RepoListViewModel
import com.gd.squareapp.ui.repolist.event.RepoListEvent
import com.gd.squareapp.ui.repolist.state.RepoListUiState
import com.gd.squareapp.ui.theme.Dimen
import com.gd.squareapp.ui.theme.ElectricRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RepoListScreen(
    viewModel: RepoListViewModel = hiltViewModel(),
) {
    val state by viewModel.repoListUiState.collectAsState(RepoListUiState())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(state.toolbarUiState.title),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ElectricRed,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                navigationIcon = {
                    if (state.toolbarUiState.isBackVisible) {
                        IconButton(onClick = { viewModel.onRepoListEvent(RepoListEvent.OnBackClick) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button_text)
                            )
                        }
                    }
                },
            )
        }
    ) { padding ->
        if (state.error != null) {
            CommonDialog { viewModel.onRepoListEvent(RepoListEvent.OnBackClick) }
        } else {
            PullToRefreshBox(
                isRefreshing = state.isLoading,
                onRefresh = { viewModel.refreshProjectList() },
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(Dimen.PaddingMedium),
                    verticalArrangement = Arrangement.spacedBy(Dimen.PaddingMedium_12),
                    modifier = Modifier.padding(padding)
                ) {
                    items(state.repos) { repo ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onRepoListEvent(RepoListEvent.OnListItemClick(repo.name))
                                }
                        ) {
                            RepoCard(repo)
                        }
                    }
                }
            }
        }
    }

    LoadingView(isLoading = state.isLoading)
}

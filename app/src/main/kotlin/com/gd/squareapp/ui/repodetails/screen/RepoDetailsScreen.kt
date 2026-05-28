package com.gd.squareapp.ui.repodetails.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.gd.squareapp.R
import com.gd.squareapp.ui.repodetails.RepoDetailsViewModel
import com.gd.squareapp.ui.repodetails.state.RepoDetailsUiState
import com.gd.squareapp.ui.theme.ElectricRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RepoDetailsScreen(viewModel: RepoDetailsViewModel = hiltViewModel()) {
    val state by viewModel.repoDetailsUiState.collectAsState(RepoDetailsUiState())

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
                        IconButton(onClick = {
                            // back press
                        }) {
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
        Text(
            text = "This is for testing uiState"
        )
        // TODO add logic to show data on screen
    }
}

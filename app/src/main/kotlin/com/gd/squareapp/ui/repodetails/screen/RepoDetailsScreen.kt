package com.gd.squareapp.ui.repodetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gd.squareapp.R
import com.gd.squareapp.ui.common.screen.LoadingView
import com.gd.squareapp.ui.repodetails.RepoDetailsViewModel
import com.gd.squareapp.ui.repodetails.event.RepoDetailsEvent
import com.gd.squareapp.ui.repodetails.state.RepoDetailsUiState
import com.gd.squareapp.ui.theme.Dimen
import com.gd.squareapp.ui.theme.Dimen.BorderWidth
import com.gd.squareapp.ui.theme.Dimen.ImageViewSize
import com.gd.squareapp.ui.theme.ElectricRed
import com.gd.squareapp.ui.theme.SoftWhite
import com.gd.squareapp.ui.theme.SurfaceGrey

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
                            viewModel.onRepoDetailsEvent(RepoDetailsEvent.OnBackClick)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(padding)
        ) {
            state.repo?.let { repo ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(Dimen.PaddingMedium)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = repo.repoOwner.avatarUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(ImageViewSize)
                                .clip(CircleShape)
                                .border(BorderWidth, ElectricRed, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(Dimen.PaddingMedium))
                        Column {
                            Text(
                                text = repo.name,
                                color = SoftWhite,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "@${repo.repoOwner.login}",
                                color = ElectricRed,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimen.PaddingLarge))

                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Dimen.PaddingMedium)
                    ) {
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Favorite,
                            label = stringResource(R.string.watcher),
                            value = repo.watchers.toString()
                        )
                        InfoCard(
                            modifier = Modifier.weight(1f),
                            icon = if (repo.isPrivate) Icons.Default.Person else Icons.Default.Person,
                            label = stringResource(R.string.visibility),
                            value = if (repo.isPrivate) stringResource(R.string.private_name) else stringResource(
                                R.string.public_name
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimen.PaddingLarge))

                    // Description
                    Text(
                        text = stringResource(R.string.about_the_repo),
                        color = SoftWhite,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(Dimen.PaddingSmall))
                    repo.description?.let {
                        Text(
                            text = it,
                            color = SoftWhite,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = Dimen.LineHeight
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimen.PaddingLarge))

                    // Link Section
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
                        shape = RoundedCornerShape(Dimen.BorderWidth)
                    ) {
                        Row(
                            modifier = Modifier.padding(Dimen.PaddingMedium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Link, contentDescription = null, tint = ElectricRed)
                            Spacer(modifier = Modifier.width(Dimen.PaddingSmall))
                            Text(
                                text = repo.gitUrl,
                                color = SoftWhite,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            if (state.isLoading) {
                LoadingView(isLoading = true)
            }
        }
    }
}

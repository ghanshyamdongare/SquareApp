package com.gd.squareapp.ui.repolist.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gd.squareapp.ui.theme.Dimen.LoadingViewSize

@Composable
fun LoadingView(isLoading: Boolean) {
    AnimatedVisibility(
        isLoading,
        enter = fadeIn() + expandVertically()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(LoadingViewSize)
                    .align(Alignment.Center),
                color = Color.Red
            )
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.DarkGray.copy(
                    alpha = .3f
                )
            ) { }
        }
    }
}

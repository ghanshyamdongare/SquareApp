package com.gd.squareapp.ui.repolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gd.domain.model.Repo
import com.gd.sqaureapp.R
import com.gd.squareapp.ui.theme.DeepBlack
import com.gd.squareapp.ui.theme.ElectricRed
import com.gd.squareapp.ui.theme.MutedGrey
import com.gd.squareapp.ui.theme.SoftWhite
import com.gd.squareapp.ui.theme.SurfaceGrey

@Composable
fun RepoCard(repo: Repo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = DeepBlack.copy(alpha = 0.4f),
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(Color.Black, SurfaceGrey.copy(alpha = 0.4f), MutedGrey),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Header: Owner Avatar + Name + Private Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Placeholder for Owner Image (Using a Red Circle with Initial)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ElectricRed),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = repo.repoOwner.login.first().uppercase(),
                        color = SoftWhite,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = repo.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = SoftWhite,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "@${repo.repoOwner.login}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SoftWhite
                    )
                }

                // Privacy Badge
                PrivacyBadge(isPrivate = repo.isPrivate)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = repo.description ?: stringResource(R.string.no_description_available),
                style = MaterialTheme.typography.bodyMedium,
                color = SoftWhite,
                maxLines = 3,
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = ElectricRed,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = repo.watchers.toString(),
                        color = SoftWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
package com.gd.squareapp.ui.repolist.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gd.squareapp.ui.theme.ElectricRed
import com.gd.squareapp.ui.theme.MutedGrey
import com.gd.squareapp.ui.theme.SoftWhite

private const val PRIVATE = "PRIVATE"
private const val PUBLIC = "PUBLIC"

@Composable
fun PrivacyBadge(isPrivate: Boolean) {
    val color = if (isPrivate) ElectricRed else MutedGrey
    val text = if (isPrivate) PRIVATE else PUBLIC
    val icon = if (isPrivate) Icons.Default.Lock else Icons.Default.Public

    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SoftWhite,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                color = SoftWhite,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

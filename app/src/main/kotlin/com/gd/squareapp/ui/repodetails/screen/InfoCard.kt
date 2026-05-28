package com.gd.squareapp.ui.repodetails.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gd.squareapp.ui.theme.Dimen
import com.gd.squareapp.ui.theme.ElectricRed
import com.gd.squareapp.ui.theme.MutedGrey
import com.gd.squareapp.ui.theme.SoftWhite
import com.gd.squareapp.ui.theme.SurfaceGrey

@Composable
internal fun InfoCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
        shape = RoundedCornerShape(Dimen.BorderWidth),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, ElectricRed.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(Dimen.PaddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = ElectricRed,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, color = MutedGrey, style = MaterialTheme.typography.labelMedium)
            Text(
                text = value,
                color = SoftWhite,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

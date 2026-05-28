package com.gd.squareapp.ui.common.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gd.squareapp.R
import com.gd.squareapp.ui.theme.DeepBlack
import com.gd.squareapp.ui.theme.Dimen.BorderWidth
import com.gd.squareapp.ui.theme.Dimen.LineHeight
import com.gd.squareapp.ui.theme.Dimen.PaddingLarge
import com.gd.squareapp.ui.theme.Dimen.PaddingMedium_12
import com.gd.squareapp.ui.theme.Dimen.PaddingSmall
import com.gd.squareapp.ui.theme.ElectricRed
import com.gd.squareapp.ui.theme.SoftWhite
import com.gd.squareapp.ui.theme.SurfaceGrey

@Composable
internal fun CommonDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(PaddingLarge),
            colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
            border = BorderStroke(BorderWidth, ElectricRed.copy(alpha = 0.5f)),
            elevation = CardDefaults.cardElevation(PaddingMedium_12)
        ) {
            Column(
                modifier = Modifier
                    .padding(PaddingSmall)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.error_dialog_title),
                    color = ElectricRed,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(PaddingMedium_12))

                Text(
                    text = stringResource(R.string.error_dialog_description),
                    color = SoftWhite,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = LineHeight,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(PaddingLarge))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ElectricRed,
                            contentColor = DeepBlack
                        ),
                        shape = RoundedCornerShape(PaddingMedium_12)
                    ) {
                        Text(
                            text = stringResource(R.string.error_dialog_button_text),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

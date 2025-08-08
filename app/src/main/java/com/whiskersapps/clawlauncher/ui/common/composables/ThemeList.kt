package com.whiskersapps.clawlauncher.ui.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.shared.view.theme.REGULAR_LABEL_STYLE
import com.whiskersapps.clawlauncher.ui.common.palette.getPaletteName


@Composable
fun ThemeList(
    title: String,
    list: List<String>,
    selectedPalette: String,
    cardShape: CardShape,
    onSelect: (paletteId: String) -> Unit
) {
    val open = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .cardShape(cardShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { open.value = !open.value }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = MaterialTheme.colorScheme.onBackground, style = REGULAR_LABEL_STYLE)

            Icon(
                modifier = Modifier.size(32.dp),
                painter = if (open.value) {
                    painterResource(R.drawable.chevron_up)
                } else {
                    painterResource(R.drawable.chevron_down)
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )

        }

        AnimatedVisibility(open.value) {
            Column {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.background,
                    thickness = 2.dp
                )

                list.forEach { palette ->
                    Row(
                        modifier = Modifier
                            .clickable { onSelect(palette) }
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = getPaletteName(palette),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = REGULAR_LABEL_STYLE
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = true)
                        )

                        RadioButton(
                            selectedPalette == palette,
                            onClick = { onSelect(palette) })
                    }
                }
            }
        }
    }
}
package com.whiskersapps.clawlauncher.shared.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.shared.model.App
import com.whiskersapps.clawlauncher.shared.view.theme.REGULAR_LABEL_STYLE
import com.whiskersapps.clawlauncher.ui.common.composables.CardShape
import com.whiskersapps.clawlauncher.ui.common.composables.cardShape
import com.whiskersapps.clawlauncher.ui.common.composables.getCardShape

@Composable
fun AppPopup(
    app: App,
    onDismiss: () -> Unit,
    onInfoClick: () -> Unit,
    onUninstallClick: () -> Unit,
    onOpenShortcut: (App.Shortcut) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        CenteredLayout(modifier = Modifier.height(IntrinsicSize.Min), centerVertically = true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .cardShape(CardShape.Single)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppIcon(app = app, size = 64.dp)

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = app.name,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = REGULAR_LABEL_STYLE
                        )
                    }
                }

                if (app.shortcuts.isNotEmpty()) {
                    app.shortcuts.forEachIndexed { index, shortcut ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .cardShape(
                                    getCardShape(
                                        index = index,
                                        listSize = app.shortcuts.size
                                    )
                                )
                                .background(MaterialTheme.colorScheme.background)
                                .clickable { onOpenShortcut(shortcut) }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            shortcut.icon?.let { icon ->
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    bitmap = remember { icon.asImageBitmap() },
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = shortcut.label,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = REGULAR_LABEL_STYLE
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .cardShape(CardShape.Single),
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = true)
                            .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                            .background(MaterialTheme.colorScheme.background)
                            .clickable { onInfoClick() }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(R.drawable.info),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            "Info",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = REGULAR_LABEL_STYLE
                        )
                    }

                    VerticalDivider(thickness = 2.dp, color = Color.Transparent)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = true)
                            .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
                            .background(MaterialTheme.colorScheme.background)
                            .clickable { onUninstallClick() }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(R.drawable.trash),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            "Uninstall",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = REGULAR_LABEL_STYLE
                        )
                    }
                }
            }
        }
    }
}
package com.whiskersapps.clawlauncher.shared.view.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.sidePadding(padding: Dp = 24.dp): Modifier {
    return this.padding(start = padding, end = padding)
}
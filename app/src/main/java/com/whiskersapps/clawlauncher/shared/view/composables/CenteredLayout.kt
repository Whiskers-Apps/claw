package com.whiskersapps.clawlauncher.shared.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.whiskersapps.clawlauncher.shared.utils.modifyWhen

@Composable
fun CenteredLayout(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    systemBarsPadded: Boolean = true,
    sidePadded: Boolean = true,
    scrollable: Boolean = true,
    centerVertically: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .modifyWhen(systemBarsPadded) { this.systemBarsPadding() }
            .modifyWhen(scrollable) { this.verticalScroll(rememberScrollState()) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (centerVertically) {
            Arrangement.Center
        } else {
            Arrangement.Top
        }
    ) {
        if (!loading) {
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .modifyWhen(sidePadded) {
                        this.sidePadding()
                    }
            ) {
                content()
            }
        }
    }
}
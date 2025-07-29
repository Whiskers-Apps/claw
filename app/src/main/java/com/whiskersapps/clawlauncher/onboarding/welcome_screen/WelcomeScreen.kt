package com.whiskersapps.clawlauncher.onboarding.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.whiskersapps.clawlauncher.onboarding.welcome_screen.composables.AppIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreenRoot(
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    WelcomeScreen {
        when (it) {
            WelcomeScreenAction.NavigateNext -> scope.launch(Dispatchers.Main) {
                pagerState.animateScrollToPage(
                    1
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    onAction: (WelcomeScreenAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppIcon()
    }
}
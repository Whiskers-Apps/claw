package com.whiskersapps.clawlauncher.ui.screens.onboarding.finish

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.whiskersapps.clawlauncher.launcher.LauncherActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun FinishOnboardingScreenRoot(
    pagerState: PagerState,
    vm: FinishOnboardingScreenVM = koinViewModel()
) {
    val activity = (LocalActivity.current as? ComponentActivity)
    val closeActivityEvent = vm.closeActivityEvent.collectAsState().value

    LaunchedEffect(closeActivityEvent) {
        if (closeActivityEvent) {
            val intent = Intent(activity, LauncherActivity::class.java)

            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    FinishOnboardingScreen { action ->
        vm.onAction(action)
    }
}

@Composable
fun FinishOnboardingScreen(onAction: (FinishOnboardingScreenAction) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { onAction(FinishOnboardingScreenAction.FinishClick) }) {
            Text("Finish")
        }
    }
}
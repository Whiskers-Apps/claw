package com.whiskersapps.clawlauncher.ui.screens.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.whiskersapps.clawlauncher.shared.view.composables.CenteredLayout
import com.whiskersapps.clawlauncher.shared.view.theme.ClawLauncherTheme
import com.whiskersapps.clawlauncher.ui.screens.onboarding.composables.PagerDots
import com.whiskersapps.clawlauncher.ui.screens.onboarding.finish.FinishOnboardingScreenRoot
import com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine.SelectEngineScreenRoot
import com.whiskersapps.clawlauncher.ui.screens.onboarding.select_theme.SelectThemeScreenRoot
import com.whiskersapps.clawlauncher.ui.screens.onboarding.welcome.WelcomeScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val vm = getViewModel<OnBoardingActivityVM>()
            val settings = vm.settings.collectAsState().value

            if (settings != null) {
                ClawLauncherTheme(settings) {
                    val pagerState = rememberPagerState(
                        pageCount = { 4 },
                        initialPage = 0
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f, fill = true)
                        ) { page ->
                                when (page) {
                                    0 -> {
                                        WelcomeScreen()
                                    }

                                    1 -> {
                                        SelectEngineScreenRoot()
                                    }

                                    2 -> {
                                        SelectThemeScreenRoot()
                                    }

                                    3 -> {
                                        FinishOnboardingScreenRoot(pagerState)
                                    }
                                }
                        }

                        PagerDots(pagerState)
                    }
                }
            }
        }
    }
}
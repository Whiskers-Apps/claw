package com.whiskersapps.clawlauncher.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.whiskersapps.clawlauncher.onboarding.composables.PagerDots
import com.whiskersapps.clawlauncher.onboarding.select_engine_screen.SelectEngineScreenRoot
import com.whiskersapps.clawlauncher.onboarding.welcome_screen.WelcomeScreenRoot
import com.whiskersapps.clawlauncher.shared.view.theme.ClawLauncherTheme
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
                        pageCount = { 3 }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .systemBarsPadding()
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f, fill = true)
                        ) { page ->
                            when (page) {
                                0 -> {
                                    WelcomeScreenRoot(pagerState)
                                }

                                1 -> {
                                    SelectEngineScreenRoot(pagerState)
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
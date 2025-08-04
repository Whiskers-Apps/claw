package com.whiskersapps.clawlauncher.ui.screens.onboarding.finish

sealed interface FinishOnboardingScreenAction {
    data object FinishClick : FinishOnboardingScreenAction
}
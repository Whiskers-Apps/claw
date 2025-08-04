package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_theme

data class SelectThemeScreenState(
    val loading: Boolean = true,
    val theme: String = "",
    val palette: String = "",
    val darkPalette: String = ""
)
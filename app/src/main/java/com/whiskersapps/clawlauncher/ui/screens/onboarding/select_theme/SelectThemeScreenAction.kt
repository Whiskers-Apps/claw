package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_theme

sealed interface SelectThemeScreenAction {
    data class ThemeSelect(val theme: String) : SelectThemeScreenAction
    data class PaletteSelect(val palette: String) : SelectThemeScreenAction
    data class DarkPaletteSelect(val palette: String) : SelectThemeScreenAction
}
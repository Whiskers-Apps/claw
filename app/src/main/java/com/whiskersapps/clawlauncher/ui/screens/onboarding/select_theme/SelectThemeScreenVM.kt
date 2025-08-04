package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_theme

import androidx.lifecycle.ViewModel
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SelectThemeScreenVM(val settingsRepo: SettingsRepo) : ViewModel() {
    private val _state = MutableStateFlow(SelectThemeScreenState())
    val state = _state.asStateFlow()

    init {
        val settings = settingsRepo.settings.value

        _state.update {
            SelectThemeScreenState(
                loading = false,
                theme = settings.theme,
                palette = settings.palette,
                darkPalette = settings.darkPalette
            )
        }
    }

    fun onAction(action: SelectThemeScreenAction) {
        when (action) {
            is SelectThemeScreenAction.ThemeSelect -> onThemeSelect(action.theme)
            is SelectThemeScreenAction.DarkPaletteSelect -> onDarkPaletteSelect(action.palette)
            is SelectThemeScreenAction.PaletteSelect -> onPaletteSelect(action.palette)
        }
    }

    private fun onThemeSelect(theme: String) {
        _state.update { it.copy(theme = theme) }
        settingsRepo.setTheme(theme)
    }

    private fun onDarkPaletteSelect(palette: String) {
        _state.update { it.copy(darkPalette = palette) }
        settingsRepo.setDarkPalette(palette)
    }

    private fun onPaletteSelect(palette: String) {
        _state.update { it.copy(palette = palette) }
        settingsRepo.setPalette(palette)
    }
}
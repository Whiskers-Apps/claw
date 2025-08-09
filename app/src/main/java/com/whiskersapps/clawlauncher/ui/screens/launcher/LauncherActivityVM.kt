package com.whiskersapps.clawlauncher.ui.screens.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LauncherActivityVM(
    settingsRepo: SettingsRepo,
) : ViewModel() {
    val settings = settingsRepo.settingsFlow.stateIn(
        viewModelScope,
        SharingStarted.Companion.Eagerly,
        null
    )
}
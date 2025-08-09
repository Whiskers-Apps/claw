package com.whiskersapps.clawlauncher.settings

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateBack
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToAbout
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToAppsSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToBookmarksSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToHomeSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToLockScreenSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToSearchEnginesSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToSecuritySettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.NavigateToStyleSettings
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.SetDefaultLauncher
import com.whiskersapps.clawlauncher.settings.SettingsScreenAction.WorkspacesClick
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SettingsScreenVM constructor(
    private val app: Application,
    private val settingsRepo: SettingsRepo,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepo.settings.collect { settings ->
                _state.update {
                    it.copy(
                        loading = false,
                        settings = settings,
                        isDefaultLauncher = isDefaultLauncher()
                    )
                }
            }
        }
    }

    fun onAction(action: SettingsScreenAction) {
        when (action) {
            NavigateBack -> {}
            NavigateToAbout -> {}
            NavigateToAppsSettings -> {}
            NavigateToBookmarksSettings -> {}
            NavigateToHomeSettings -> {}
            NavigateToSearchEnginesSettings -> {}
            NavigateToStyleSettings -> {}
            NavigateToSecuritySettings -> {}
            SetDefaultLauncher -> setDefaultLauncher()
            NavigateToLockScreenSettings -> {}
            WorkspacesClick -> {}
        }
    }

    private fun isDefaultLauncher(): Boolean {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
        }

        val resolve = app.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolve?.activityInfo?.packageName == app.packageName
    }

    private fun setDefaultLauncher() {

        val intent = Intent(Settings.ACTION_HOME_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        app.startActivity(intent)

        _state.update { it.copy(isDefaultLauncher = isDefaultLauncher()) }
    }
}
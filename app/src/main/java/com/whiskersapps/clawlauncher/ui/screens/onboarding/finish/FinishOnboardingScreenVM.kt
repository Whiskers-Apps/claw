package com.whiskersapps.clawlauncher.ui.screens.onboarding.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FinishOnboardingScreenVM(val settingsRepo: SettingsRepo) : ViewModel() {
    var alreadyClicked = false

    private val _closeActivityEvent = MutableStateFlow(false)
    val closeActivityEvent = _closeActivityEvent.asStateFlow()

    fun onAction(action: FinishOnboardingScreenAction) {
        when (action) {
            FinishOnboardingScreenAction.FinishClick -> onFinishClick()
        }
    }

    private fun onFinishClick() {
        if (!alreadyClicked) {
            alreadyClicked = true

            settingsRepo.setSetupCompleted(true)

            viewModelScope.launch(Dispatchers.Main) {
                _closeActivityEvent.emit(true)
            }
        }
    }
}
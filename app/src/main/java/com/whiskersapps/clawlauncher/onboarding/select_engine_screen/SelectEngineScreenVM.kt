package com.whiskersapps.clawlauncher.onboarding.select_engine_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whiskersapps.clawlauncher.launcher.search_engines.SearchEnginesRepo
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectEngineScreenVM(
    private val searchEnginesRepo: SearchEnginesRepo,
    private val settingsRepo: SettingsRepo
) : ViewModel() {
    private val _state = MutableStateFlow(SelectEngineScreenState())
    val state = _state.asStateFlow()

    private val ioScope = CoroutineScope(Dispatchers.IO)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchEnginesRepo.searchEngines.collect { searchEngines ->
                _state.update {
                    it.copy(
                        loading = false,
                        searchEngines = searchEngines,
                        selectedEngine = searchEnginesRepo.getDefaultEngine()
                    )
                }
            }
        }
    }

    fun onAction(action: SelectEngineScreenAction) {
        when (action) {
            SelectEngineScreenAction.Finish -> finishSetup()
            SelectEngineScreenAction.NavigateBack -> {}
            is SelectEngineScreenAction.SetDefaultEngine -> setDefaultEngine(action.id)
        }
    }

    private fun finishSetup() {
        settingsRepo.setSetupCompleted(true)
    }

    private fun setDefaultEngine(id: Int) {
        searchEnginesRepo.setDefault(id)
    }
}
package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine

import com.whiskersapps.clawlauncher.shared.model.SearchEngine

data class SelectEngineScreenState(
    val loading: Boolean = true,
    val searchEngines: List<SearchEngine> = emptyList(),
    val defaultEngine: SearchEngine? = null
)
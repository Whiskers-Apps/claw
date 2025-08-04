package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine

sealed class SelectEngineScreenAction {
    data object Finish : SelectEngineScreenAction()
    data object NavigateBack : SelectEngineScreenAction()
    data class SetDefaultEngine(val id: Int) : SelectEngineScreenAction()
}
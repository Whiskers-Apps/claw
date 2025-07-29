package com.whiskersapps.clawlauncher.onboarding.select_engine_screen

sealed class SelectEngineScreenAction {
    data object Finish : SelectEngineScreenAction()
    data object NavigateBack : SelectEngineScreenAction()
    data class SetDefaultEngine(val id: Int) : SelectEngineScreenAction()
}
package com.whiskersapps.clawlauncher.settings.search_engines

import com.whiskersapps.clawlauncher.shared.model.SearchEngine

data class SearchEnginesScreenState(
    val loading: Boolean = true,
    val searchEngines: List<SearchEngine> = emptyList(),
    val defaultSearchEngineId: Int? = null,
    val defaultSearchEngine: SearchEngine? = null,
    val addEngineDialog: AddEngineDialog = AddEngineDialog(),
    val editEngineDialog: EditEngineDialog = EditEngineDialog()
) {
    data class AddEngineDialog(
        val show: Boolean = false,
        val name: String = "",
        val query: String = ""
    )

    data class EditEngineDialog(
        val id: Int = -1,
        val show: Boolean = false,
        val name: String = "",
        val query: String = "",
        val defaultEngine: Boolean = false
    )
}
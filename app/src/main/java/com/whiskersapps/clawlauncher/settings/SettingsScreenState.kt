package com.whiskersapps.clawlauncher.settings

import com.whiskersapps.clawlauncher.shared.model.Bookmark
import com.whiskersapps.clawlauncher.shared.model.Group
import com.whiskersapps.clawlauncher.shared.model.SearchEngine
import com.whiskersapps.clawlauncher.shared.model.Settings

data class SettingsScreenState(
    val loading: Boolean = true,
    val settings: Settings = Settings(),
    val searchEngines: List<SearchEngine> = emptyList(),
    val bookmarks: List<Bookmark> = emptyList(),
    val bookmarkGroups: List<Group> = emptyList(),
    val isDefaultLauncher: Boolean = false
)
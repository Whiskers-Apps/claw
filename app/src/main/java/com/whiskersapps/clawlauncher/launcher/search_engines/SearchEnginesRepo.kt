package com.whiskersapps.clawlauncher.launcher.search_engines

import com.whiskersapps.clawlauncher.core.db.AppDB
import com.whiskersapps.clawlauncher.settings.di.SettingsRepo
import com.whiskersapps.clawlauncher.shared.model.SearchEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchEnginesRepo(
    db: AppDB,
    private val settingsRepo: SettingsRepo
) {
    private val dao = db.searchEnginesDao()
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _searchEngines = MutableStateFlow<List<SearchEngine>>(emptyList())
    val searchEngines = _searchEngines.asStateFlow()

    init {
        ioScope.launch {
            dao.getAll().collect { dbEngines ->
                _searchEngines.update { dbEngines }
                println(dbEngines)
            }
        }
    }

    fun getDefaultEngine(): SearchEngine? {
        return searchEngines.value.find { it.default }
    }

    fun setDefault(id: Int) {
        ioScope.launch {
            val currentDefault = getDefaultEngine()

            currentDefault?.let { searchEngine ->
                dao.setDefault(false, currentDefault.id)
            }

            dao.setDefault(true, id)
        }
    }

    fun addEngine(searchEngine: SearchEngine) {
        ioScope.launch {
            dao.insert(searchEngine)
        }
    }

    fun updateSearchEngine(id: Int, name: String, query: String) {

    }

    fun deleteSearchEngine(searchEngine: SearchEngine) {
        ioScope.launch {
            dao.delete(searchEngine)
        }
    }
}
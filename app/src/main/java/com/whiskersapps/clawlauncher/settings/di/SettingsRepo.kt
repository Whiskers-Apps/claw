package com.whiskersapps.clawlauncher.settings.di

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.whiskersapps.clawlauncher.core.db.AppDB
import com.whiskersapps.clawlauncher.shared.model.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore("settings")

class SettingsRepo(
    app: Application,
    private val db: AppDB
) {
    private val dataStore = app.dataStore

    private val _settings = MutableStateFlow(Settings())
    val settings = _settings.asStateFlow()

    private val ioScope = CoroutineScope(Dispatchers.IO)

    val settingsFlow: Flow<Settings> = dataStore.data
        .catch {
            Settings()
            _settings.update { Settings() }
        }
        .map { preferences ->

            val newSettings = Settings(
                setupCompleted = preferences[Settings.SETUP_COMPLETED]
                    ?: Settings.DEFAULT_SETUP_COMPLETED,

                appsViewType = preferences[Settings.APPS_VIEW_TYPE]
                    ?: Settings.DEFAULT_APPS_VIEW_TYPE,

                portraitCols = preferences[Settings.PORTRAIT_COLS]
                    ?: Settings.DEFAULT_PORTRAIT_COLS,

                landscapeCols = preferences[Settings.LANDSCAPE_COLS]
                    ?: Settings.DEFAULT_LANDSCAPE_COLS,

                unfoldedPortraitCols = preferences[Settings.UNFOLDED_PORTRAIT_COLS]
                    ?: Settings.DEFAULT_UNFOLDED_PORTRAIT_COLS,

                unfoldedLandscapeCols = preferences[Settings.UNFOLDED_LANDSCAPE_COLS]
                    ?: Settings.DEFAULT_UNFOLDED_LANDSCAPE_COLS,

                showHomeSearchBar = preferences[Settings.SHOW_HOME_SEARCH_BAR]
                    ?: Settings.DEFAULT_SHOW_HOME_SEARCH_BAR,

                showHomeSearchBarPlaceholder = preferences[Settings.SHOW_HOME_SEARCH_BAR_PLACEHOLDER]
                    ?: Settings.DEFAULT_SHOW_HOME_SEARCH_BAR_PLACEHOLDER,

                homeSearchBarRadius = preferences[Settings.HOME_SEARCH_BAR_RADIUS]
                    ?: Settings.DEFAULT_HOME_SEARCH_BAR_RADIUS,

                showAppsSearchBar = preferences[Settings.SHOW_APPS_SEARCH_BAR]
                    ?: Settings.DEFAULT_SHOW_APPS_SEARCH_BAR,

                appsSearchBarPosition = preferences[Settings.APPS_SEARCH_BAR_POSITION]
                    ?: Settings.DEFAULT_APPS_SEARCH_BAR_POSITION,

                showAppsSearchBarPlaceholder = preferences[Settings.SHOW_APPS_SEARCH_BAR_PLACEHOLDER]
                    ?: Settings.DEFAULT_SHOW_APPS_SEARCH_BAR_PLACEHOLDER,

                appsSearchBarRadius = preferences[Settings.APPS_SEARCH_BAR_RADIUS]
                    ?: Settings.DEFAULT_APPS_SEARCH_BAR_RADIUS,

                theme = preferences[Settings.THEME] ?: Settings.DEFAULT_THEME,

                palette = preferences[Settings.PALETTE] ?: Settings.DEFAULT_PALETTE,

                darkPalette = preferences[Settings.DARK_PALETTE] ?: Settings.DEFAULT_DARK_PALETTE,

                hiddenApps = getHiddenApps(),

                secureApps = getSecureApps(),

                swipeUpToSearch = preferences[Settings.SWIPE_UP_TO_SEARCH]
                    ?: Settings.DEFAULT_SWIPE_UP_TO_SEARCH,

                disableAppsScreen = preferences[Settings.DISABLE_APPS_SCREEN]
                    ?: Settings.DEFAULT_DISABLE_APPS_SCREEN,

                tintClock = preferences[Settings.TINT_CLOCK] ?: Settings.DEFAULT_TINT_CLOCK,

                splitListView = preferences[Settings.SPLIT_LIST_VIEW]
                    ?: Settings.DEFAULT_SPLIT_LIST_VIEW,

                clockPlacement = preferences[Settings.CLOCK_PLACEMENT]
                    ?: Settings.DEFAULT_CLOCK_PLACEMENT,

                pillShapeClock = preferences[Settings.PILL_SHAPE_CLOCK]
                    ?: Settings.DEFAULT_PILL_SHAPE_CLOCK,

                hideAppLabels = preferences[Settings.HIDE_APP_LABELS]
                    ?: Settings.DEFAULT_HIDE_APP_LABELS,

                iconPack = preferences[Settings.ICON_PACK] ?: Settings.DEFAULT_ICON_PACK
            )

            _settings.update { newSettings }

            newSettings
        }

    fun setSetupCompleted(setupCompleted: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SETUP_COMPLETED] = setupCompleted }
        }
    }

    fun setAppsViewType(appsViewType: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.APPS_VIEW_TYPE] = appsViewType }
        }
    }

    fun setPortraitCols(cols: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.PORTRAIT_COLS] = cols }
        }
    }

    fun setLandscapeCols(cols: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.LANDSCAPE_COLS] = cols }
        }
    }

    fun setUnfoldedCols(cols: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.UNFOLDED_PORTRAIT_COLS] = cols }
        }
    }

    fun setUnfoldedLandscapeCols(cols: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.UNFOLDED_LANDSCAPE_COLS] = cols }
        }
    }

    fun setShowHomeSearchBar(show: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SHOW_HOME_SEARCH_BAR] = show }
        }
    }

    fun setShowHomeSearchBarPlaceholder(show: Boolean) {
        ioScope.launch {
            dataStore.edit {
                it[Settings.SHOW_HOME_SEARCH_BAR_PLACEHOLDER] = show
            }
        }
    }

    fun setHomeSearchBarRadius(radius: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.HOME_SEARCH_BAR_RADIUS] = radius }
        }
    }

    fun setShowAppsSearchBar(showAppsSearchBar: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SHOW_APPS_SEARCH_BAR] = showAppsSearchBar }
        }
    }

    fun setAppsSearchBarPosition(appsSearchBarPosition: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.APPS_SEARCH_BAR_POSITION] = appsSearchBarPosition }
        }
    }

    fun setShowAppsSearchBarPlaceholder(show: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SHOW_APPS_SEARCH_BAR_PLACEHOLDER] = show }
        }
    }

    fun setAppsSearchBarRadius(radius: Int) {
        ioScope.launch {
            dataStore.edit { it[Settings.APPS_SEARCH_BAR_RADIUS] = radius }
        }
    }

    fun setTheme(theme: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.THEME] = theme }
        }
    }

    fun setPalette(palette: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.PALETTE] = palette }
        }
    }

    fun setDarkPalette(palette: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.DARK_PALETTE] = palette }
        }
    }

    private fun getHiddenApps(): List<String> {
//        return realm.query<SecuritySettings>().find().firstOrNull()?.hiddenApps ?: emptyList()
        return emptyList()
    }

    fun setHiddenApps(apps: List<String>) {
//        realm.writeBlocking {
//            val securitySettings = query<SecuritySettings>().find().firstOrNull()
//
//            if (securitySettings == null) {
//                val settings = SecuritySettings().apply {
//                    hiddenApps = apps.toRealmList()
//                }
//
//                copyToRealm(settings)
//            } else {
//                securitySettings.hiddenApps = apps.toRealmList()
//            }
//        }
//
//        _settings.update { it.copy(hiddenApps = apps) }
    }

    private fun getSecureApps(): List<String> {
//        return realm.query<SecuritySettings>().first().find()?.secureApps ?: emptyList()
        return emptyList()
    }

    fun setSecureApps(apps: List<String>) {
//        realm.writeBlocking {
//            val securitySettings = query<SecuritySettings>().find().firstOrNull()
//
//            if (securitySettings == null) {
//                val settings = SecuritySettings().apply {
//                    secureApps = apps.toRealmList()
//                }
//
//                copyToRealm(settings)
//            } else {
//                securitySettings.secureApps = apps.toRealmList()
//            }
//        }
//
//        _settings.update { it.copy(secureApps = apps) }
    }

    fun setSwipeUpToSearch(swipeUp: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SWIPE_UP_TO_SEARCH] = swipeUp }
        }
    }

    fun setDisableAppsScreen(disable: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.DISABLE_APPS_SCREEN] = disable }
        }
    }

    fun setTintClock(tint: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.TINT_CLOCK] = tint }
        }
    }

    fun setSplitList(split: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.SPLIT_LIST_VIEW] = split }
        }
    }

    fun setClockPlacement(placement: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.CLOCK_PLACEMENT] = placement }
        }
    }

    fun setPillShapeClock(pill: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.PILL_SHAPE_CLOCK] = pill }
        }
    }

    fun setHideAppLabels(hide: Boolean) {
        ioScope.launch {
            dataStore.edit { it[Settings.HIDE_APP_LABELS] = hide }
        }
    }

    fun setIconPack(packageName: String) {
        ioScope.launch {
            dataStore.edit { it[Settings.ICON_PACK] = packageName }
        }
    }
}
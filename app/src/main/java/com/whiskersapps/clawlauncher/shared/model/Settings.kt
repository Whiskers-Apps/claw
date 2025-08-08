package com.whiskersapps.clawlauncher.shared.model

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.whiskersapps.clawlauncher.data.settings.SettingsValues
import com.whiskersapps.clawlauncher.shared.utils.isAtLeastAndroid12

data class Settings(
    val setupCompleted: Boolean = DEFAULT_SETUP_COMPLETED,

    val appsViewType: String = DEFAULT_APPS_VIEW_TYPE,

    val portraitCols: Int = DEFAULT_PORTRAIT_COLS,

    val landscapeCols: Int = DEFAULT_LANDSCAPE_COLS,

    val unfoldedPortraitCols: Int = DEFAULT_UNFOLDED_PORTRAIT_COLS,

    val unfoldedLandscapeCols: Int = DEFAULT_UNFOLDED_LANDSCAPE_COLS,

    val showHomeSearchBar: Boolean = DEFAULT_SHOW_HOME_SEARCH_BAR,

    val showHomeSearchBarPlaceholder: Boolean = DEFAULT_SHOW_HOME_SEARCH_BAR_PLACEHOLDER,

    val homeSearchBarRadius: Int = DEFAULT_HOME_SEARCH_BAR_RADIUS,

    val showAppsSearchBar: Boolean = DEFAULT_SHOW_APPS_SEARCH_BAR,

    val showAppsSearchBarPlaceholder: Boolean = DEFAULT_SHOW_APPS_SEARCH_BAR_PLACEHOLDER,

    val appsSearchBarPosition: String = DEFAULT_APPS_SEARCH_BAR_POSITION,

    val appsSearchBarRadius: Int = DEFAULT_APPS_SEARCH_BAR_RADIUS,

    val theme: String = DEFAULT_THEME,

    val palette: String = DEFAULT_PALETTE,

    val darkPalette: String = DEFAULT_DARK_PALETTE,

    val hiddenApps: List<String> = emptyList(),

    val secureApps: List<String> = emptyList(),

    val swipeUpToSearch: Boolean = DEFAULT_SWIPE_UP_TO_SEARCH,

    val disableAppsScreen: Boolean = DEFAULT_DISABLE_APPS_SCREEN,

    val tintClock: Boolean = DEFAULT_TINT_CLOCK,

    val splitListView: Boolean = DEFAULT_SPLIT_LIST_VIEW,

    val clockPlacement: String = DEFAULT_CLOCK_PLACEMENT,

    val pillShapeClock: Boolean = DEFAULT_PILL_SHAPE_CLOCK,

    val hideAppLabels: Boolean = DEFAULT_HIDE_APP_LABELS,

    val iconPack: String = DEFAULT_ICON_PACK
) {
    companion object {
        val SETUP_COMPLETED = booleanPreferencesKey("setup-completed")
        const val DEFAULT_SETUP_COMPLETED = false

        val APPS_VIEW_TYPE = stringPreferencesKey("apps-view-type")
        const val DEFAULT_APPS_VIEW_TYPE = "grid"

        val PORTRAIT_COLS = intPreferencesKey("portrait-cols")
        const val DEFAULT_PORTRAIT_COLS = 4

        val LANDSCAPE_COLS = intPreferencesKey("landscape-cols")
        const val DEFAULT_LANDSCAPE_COLS = 7

        val UNFOLDED_PORTRAIT_COLS = intPreferencesKey("unfolded-portrait-cols")
        const val DEFAULT_UNFOLDED_PORTRAIT_COLS = 7

        val UNFOLDED_LANDSCAPE_COLS = intPreferencesKey("unfolded-landscape-cols")
        const val DEFAULT_UNFOLDED_LANDSCAPE_COLS = 7

        val SHOW_HOME_SEARCH_BAR = booleanPreferencesKey("show-home-search-bar")
        const val DEFAULT_SHOW_HOME_SEARCH_BAR = true

        val SHOW_HOME_SEARCH_BAR_PLACEHOLDER =
            booleanPreferencesKey("show-home-search-bar-placeholder")
        const val DEFAULT_SHOW_HOME_SEARCH_BAR_PLACEHOLDER = true

        val HOME_SEARCH_BAR_RADIUS = intPreferencesKey("home-search-bar-radius")
        const val DEFAULT_HOME_SEARCH_BAR_RADIUS = 50

        val SHOW_APPS_SEARCH_BAR = booleanPreferencesKey("show-apps-search-bar")
        const val DEFAULT_SHOW_APPS_SEARCH_BAR = true

        val SHOW_APPS_SEARCH_BAR_PLACEHOLDER =
            booleanPreferencesKey("show-apps-search-bar-placeholder")
        const val DEFAULT_SHOW_APPS_SEARCH_BAR_PLACEHOLDER = true

        val APPS_SEARCH_BAR_POSITION = stringPreferencesKey("apps-search-bar-position")
        const val DEFAULT_APPS_SEARCH_BAR_POSITION = SettingsValues.AppsSearchBarPosition.BOTTOM

        val APPS_SEARCH_BAR_RADIUS = intPreferencesKey("apps-search-bar-radius")
        const val DEFAULT_APPS_SEARCH_BAR_RADIUS = 50

        val THEME = stringPreferencesKey("theme")
        const val DEFAULT_THEME = SettingsValues.Theme.SYSTEM

        val PALETTE = stringPreferencesKey("palette")
        val DEFAULT_PALETTE =
            if (isAtLeastAndroid12()) SettingsValues.Palette.MONET else SettingsValues.Palette.LYNX_YELLOW

        val DARK_PALETTE = stringPreferencesKey("dark-palette")
        val DEFAULT_DARK_PALETTE =
            if (isAtLeastAndroid12()) SettingsValues.DarkPalette.MONET else SettingsValues.DarkPalette.PANTHER_YELLOW

        val SWIPE_UP_TO_SEARCH = booleanPreferencesKey("swipe-up-to-search")
        const val DEFAULT_SWIPE_UP_TO_SEARCH = true

        val DISABLE_APPS_SCREEN = booleanPreferencesKey("disable-apps-screen")
        const val DEFAULT_DISABLE_APPS_SCREEN = false

        val TINT_CLOCK = booleanPreferencesKey("tint-clock")
        const val DEFAULT_TINT_CLOCK = false

        val SPLIT_LIST_VIEW = booleanPreferencesKey("split-list-view")
        const val DEFAULT_SPLIT_LIST_VIEW = true

        val CLOCK_PLACEMENT = stringPreferencesKey("clock-placement")
        const val DEFAULT_CLOCK_PLACEMENT = SettingsValues.ClockPlacement.TOP

        val PILL_SHAPE_CLOCK = booleanPreferencesKey("pill-shape-clock")
        const val DEFAULT_PILL_SHAPE_CLOCK = true

        val HIDE_APP_LABELS = booleanPreferencesKey("hide-app-labels")
        const val DEFAULT_HIDE_APP_LABELS = false

        val ICON_PACK = stringPreferencesKey("icon-pack")
        const val DEFAULT_ICON_PACK = SettingsValues.IconPack.SYSTEM
    }
}
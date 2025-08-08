package com.whiskersapps.clawlauncher.shared.view.theme

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.whiskersapps.clawlauncher.data.settings.SettingsValues
import com.whiskersapps.clawlauncher.shared.model.Settings
import com.whiskersapps.clawlauncher.shared.utils.isAtLeastAndroid12
import com.whiskersapps.clawlauncher.ui.common.palette.LynxPalettes
import com.whiskersapps.clawlauncher.ui.common.palette.PantherPalettes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.monocode.lib.ThemeId
import org.monocode.lib.getMaterialMonoCode

fun getPalette(palette: String): ColorScheme {
    return when (palette) {
        SettingsValues.Palette.LYNX_RED -> getMaterialMonoCode(ThemeId.LynxRed)
        SettingsValues.Palette.LYNX_ORANGE -> getMaterialMonoCode(ThemeId.LynxOrange)
        SettingsValues.Palette.LYNX_YELLOW -> getMaterialMonoCode(ThemeId.LynxYellow)
        SettingsValues.Palette.LYNX_GREEN -> getMaterialMonoCode(ThemeId.LynxGreen)
        SettingsValues.Palette.LYNX_NEON_GREEN -> getMaterialMonoCode(ThemeId.LynxNeonGreen)
        SettingsValues.Palette.LYNX_BLUE -> getMaterialMonoCode(ThemeId.LynxBlue)
        SettingsValues.Palette.LYNX_CYAN -> getMaterialMonoCode(ThemeId.LynxCyan)
        SettingsValues.Palette.LYNX_PURPLE -> getMaterialMonoCode(ThemeId.LynxPurple)
        SettingsValues.Palette.LYNX_PINK -> getMaterialMonoCode(ThemeId.LynxPink)
        SettingsValues.DarkPalette.PANTHER_RED -> getMaterialMonoCode(ThemeId.PantherRed)
        SettingsValues.DarkPalette.PANTHER_ORANGE -> getMaterialMonoCode(ThemeId.PantherOrange)
        SettingsValues.DarkPalette.PANTHER_YELLOW -> getMaterialMonoCode(ThemeId.PantherYellow)
        SettingsValues.DarkPalette.PANTHER_GREEN -> getMaterialMonoCode(ThemeId.PantherGreen)
        SettingsValues.DarkPalette.PANTHER_NEON_GREEN -> getMaterialMonoCode(ThemeId.PantherNeonGreen)
        SettingsValues.DarkPalette.PANTHER_BLUE -> getMaterialMonoCode(ThemeId.PantherBlue)
        SettingsValues.DarkPalette.PANTHER_CYAN -> getMaterialMonoCode(ThemeId.PantherCyan)
        SettingsValues.DarkPalette.PANTHER_PURPLE -> getMaterialMonoCode(ThemeId.PantherPurple)
        SettingsValues.DarkPalette.PANTHER_PINK -> getMaterialMonoCode(ThemeId.PantherPink)
        else -> throw IllegalArgumentException()
    }
}

@Composable
fun ClawLauncherTheme(
    settings: Settings,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val darkMode = when (settings.theme) {
        "light" -> AppCompatDelegate.MODE_NIGHT_NO
        "dark" -> AppCompatDelegate.MODE_NIGHT_YES
        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    AppCompatDelegate.setDefaultNightMode(darkMode)

    val useMonet = settings.palette == "monet"
    val useDarkMonet = settings.darkPalette == "monet"
    val useDarkTheme = useDarkTheme(darkMode = settings.theme)
    val view = LocalView.current
    val window = (view.context as Activity).window
    val scope = rememberCoroutineScope()

    SideEffect {
        scope.launch(Dispatchers.Main) {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = if (useMonet && isAtLeastAndroid12() && !useDarkTheme) {
            dynamicLightColorScheme(context)
        } else if (useDarkMonet && isAtLeastAndroid12() && useDarkTheme) {
            dynamicDarkColorScheme(context)
        } else if (!useDarkTheme && LynxPalettes.contains(settings.palette)) {
            getPalette(settings.palette)
        } else if (useDarkTheme && PantherPalettes.contains(settings.darkPalette)) {
            getPalette(settings.darkPalette)
        } else {
            if (useDarkTheme)
                getDarkColorScheme(id = settings.darkPalette)
            else
                getLightColorScheme(id = settings.palette)
        },
        typography = Typography,
        content = content
    )
}

@Composable
fun PreviewTheme(
    useMonet: Boolean,
    useDarkMonet: Boolean,
    dark: Boolean,
    theme: String,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    MaterialTheme(
        colorScheme = if (useMonet && isAtLeastAndroid12() && !dark) {
            dynamicLightColorScheme(context)
        } else if (useDarkMonet && isAtLeastAndroid12() && dark) {
            dynamicDarkColorScheme(context)
        } else {
            if (dark) getDarkColorScheme(id = theme) else getLightColorScheme(id = theme)
        },
        typography = Typography,
        content = content
    )
}

@Composable
fun useDarkTheme(darkMode: String) =
    darkMode == "system" && isSystemInDarkTheme() || darkMode == "dark"

@Composable
fun getDarkColorScheme(id: String): ColorScheme {
    val context = LocalContext.current

    return if (id == "monet" && isAtLeastAndroid12()) {
        dynamicDarkColorScheme(context)
    } else {
//        val theme = CUSTOM_THEMES.find { it.id == id }!!

//        darkColorScheme(
//            background = theme.background,
//            surfaceVariant = theme.secondaryBackground,
//            onBackground = theme.text,
//            onSurfaceVariant = theme.text,
//            primary = theme.accent,
//            onPrimary = theme.onAccent
//        )
        darkColorScheme()
    }
}

@Composable
fun getLightColorScheme(id: String): ColorScheme {
    val context = LocalContext.current

    return if (id == "monet" && isAtLeastAndroid12()) {
        dynamicLightColorScheme(context)
    } else {
//        val theme = CUSTOM_THEMES.find { it.id == id }!!
//
//        lightColorScheme(
//            background = theme.background,
//            surfaceVariant = theme.secondaryBackground,
//            onBackground = theme.text,
//            onSurfaceVariant = theme.text,
//            primary = theme.accent,
//            onPrimary = theme.onAccent
//        )
        lightColorScheme()
    }
}

fun getThemeDisplayName(id: String): String {
    return when (id) {
        "tiger-banana" -> "Tiger Banana"
        "tiger-blueberry" -> "Tiger Blueberry"
        "tiger-cherry" -> "Tiger Cherry"
        "tiger-grape" -> "Tiger Grape"
        "tiger-kiwi" -> "Tiger Kiwi"
        "tiger-tangerine" -> "Tiger Tangerine"
        "panther-banana" -> "Panther Banana"
        "panther-blueberry" -> "Panther Blueberry"
        "panther-cherry" -> "Panther Cherry"
        "panther-grape" -> "Panther Grape"
        "panther-kiwi" -> "Panther Kiwi"
        "panther-tangerine" -> "Panther Tangerine"
        else -> "Material You"
    }
}
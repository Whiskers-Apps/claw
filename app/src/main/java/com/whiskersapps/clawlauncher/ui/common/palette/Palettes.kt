package com.whiskersapps.clawlauncher.ui.common.palette

import com.whiskersapps.clawlauncher.data.settings.SettingsValues

val PantherPalettes = listOf(
    SettingsValues.DarkPalette.PANTHER_RED,
    SettingsValues.DarkPalette.PANTHER_ORANGE,
    SettingsValues.DarkPalette.PANTHER_YELLOW,
    SettingsValues.DarkPalette.PANTHER_GREEN,
    SettingsValues.DarkPalette.PANTHER_NEON_GREEN,
    SettingsValues.DarkPalette.PANTHER_BLUE,
    SettingsValues.DarkPalette.PANTHER_CYAN,
    SettingsValues.DarkPalette.PANTHER_PURPLE,
    SettingsValues.DarkPalette.PANTHER_PINK,
)

val LynxPalettes = listOf(
    SettingsValues.Palette.LYNX_RED,
    SettingsValues.Palette.LYNX_ORANGE,
    SettingsValues.Palette.LYNX_YELLOW,
    SettingsValues.Palette.LYNX_GREEN,
    SettingsValues.Palette.LYNX_NEON_GREEN,
    SettingsValues.Palette.LYNX_BLUE,
    SettingsValues.Palette.LYNX_CYAN,
    SettingsValues.Palette.LYNX_PURPLE,
    SettingsValues.Palette.LYNX_PINK,
)

fun getPaletteName(palette: String): String {
    return when (palette) {
        SettingsValues.Palette.LYNX_RED -> "Lynx Red"
        SettingsValues.Palette.LYNX_ORANGE -> "Lynx Orange"
        SettingsValues.Palette.LYNX_YELLOW -> "Lynx Yellow"
        SettingsValues.Palette.LYNX_GREEN -> "Lynx Green"
        SettingsValues.Palette.LYNX_NEON_GREEN -> "Lynx Neon Green"
        SettingsValues.Palette.LYNX_CYAN -> "Lynx Cyan"
        SettingsValues.Palette.LYNX_BLUE -> "Lynx Blue"
        SettingsValues.Palette.LYNX_PURPLE -> "Lynx Purple"
        SettingsValues.Palette.LYNX_PINK -> "Lynx Pink"
        SettingsValues.DarkPalette.PANTHER_RED -> "Panther Red"
        SettingsValues.DarkPalette.PANTHER_ORANGE -> "Panther Orange"
        SettingsValues.DarkPalette.PANTHER_YELLOW -> "Panther Yellow"
        SettingsValues.DarkPalette.PANTHER_GREEN -> "Panther Green"
        SettingsValues.DarkPalette.PANTHER_NEON_GREEN -> "Panther Neon Green"
        SettingsValues.DarkPalette.PANTHER_CYAN -> "Panther Cyan"
        SettingsValues.DarkPalette.PANTHER_BLUE -> "Panther Blue"
        SettingsValues.DarkPalette.PANTHER_PURPLE -> "Panther Purple"
        SettingsValues.DarkPalette.PANTHER_PINK -> "Panther Pink"
        else -> {
            throw IllegalArgumentException("Palette Not Available")
        }
    }
}
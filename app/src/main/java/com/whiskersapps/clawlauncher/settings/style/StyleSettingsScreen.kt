package com.whiskersapps.clawlauncher.settings.style

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.BackClicked
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.CloseDarkModeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.CloseDarkThemeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.CloseThemeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.IconPackClicked
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.IconPackDialogClosed
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.IconPackSelected
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.OpenDarkModeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.OpenDarkThemeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.OpenThemeDialog
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent.SetDarkMode
import com.whiskersapps.clawlauncher.settings.style.composables.DarkModeDialog
import com.whiskersapps.clawlauncher.settings.style.composables.IconPackDialog
import com.whiskersapps.clawlauncher.settings.style.composables.ThemeDialog
import com.whiskersapps.clawlauncher.shared.view.composables.CenteredLayout
import com.whiskersapps.clawlauncher.shared.view.composables.NavBar
import com.whiskersapps.clawlauncher.shared.view.composables.SimpleSetting
import com.whiskersapps.clawlauncher.ui.common.palette.getPaletteName
import org.koin.androidx.compose.koinViewModel
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenIntent as Action
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenState as State
import com.whiskersapps.clawlauncher.settings.style.StyleSettingsScreenVM as ViewModel

@Composable
fun StyleSettingsScreenRoot(
    navController: NavController,
    vm: ViewModel = koinViewModel()
) {
    StyleSettingsScreen(
        state = vm.state.collectAsState().value
    ) { action ->
        when (action) {
            BackClicked -> navController.navigateUp()
            else -> vm.onAction(action)
        }
    }
}

@Composable
fun StyleSettingsScreen(
    state: State,
    onAction: (Action) -> Unit,
) {
    if (state.showIconPackDialog) {
        IconPackDialog(
            onDismiss = {
                onAction(IconPackDialogClosed)
            },
            iconPacks = state.iconPacks,
            onIconPackSelected = { iconPack ->
                onAction(IconPackSelected(iconPack))
            }
        )
    }

    CenteredLayout(sidePadded = false) {
        NavBar(navigateBack = { onAction(BackClicked) })

        Spacer(modifier = Modifier.height(16.dp))

        SimpleSetting(
            title = stringResource(R.string.StyleSettings_dark_mode),
            value = getDarkModeDisplayName(state.darkMode),
            onClick = { onAction(OpenDarkModeDialog) }
        )

        SimpleSetting(
            title = stringResource(R.string.StyleSettings_light_theme),
            value = getPaletteName(state.palette),
            onClick = { onAction(OpenThemeDialog) }
        )

        SimpleSetting(
            title = stringResource(R.string.StyleSettings_dark_theme),
            value = getPaletteName(state.darkPalette),
            onClick = { onAction(OpenDarkThemeDialog) }
        )

        SimpleSetting(
            title = "Icon Pack",
            value = state.iconPack,
            onClick = {
                onAction(IconPackClicked)
            }
        )

        DarkModeDialog(
            show = state.showDarkModeDialog,
            onDismiss = { onAction(CloseDarkModeDialog) },
            save = { darkMode -> onAction(SetDarkMode(darkMode)) },
            defaultValue = state.darkMode
        )

        ThemeDialog(
            show = state.showThemeDialog,
            onDismiss = { onAction(CloseThemeDialog) },
            state = state,
            onAction = { onAction(it) }
        )

        ThemeDialog(
            showDarkThemes = true,
            show = state.showDarkThemeDialog,
            onDismiss = { onAction(CloseDarkThemeDialog) },
            state = state,
            onAction = { onAction(it) }
        )
    }
}

@Composable
fun getDarkModeDisplayName(theme: String): String {
    return when (theme) {
        "system" -> stringResource(R.string.StyleSettings_system)
        "light" -> stringResource(R.string.StyleSettings_light)
        else -> stringResource(R.string.StyleSettings_dark)
    }
}
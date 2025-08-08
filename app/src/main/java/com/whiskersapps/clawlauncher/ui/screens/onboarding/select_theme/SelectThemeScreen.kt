package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.data.settings.SettingsValues
import com.whiskersapps.clawlauncher.ui.common.composables.CardShape
import com.whiskersapps.clawlauncher.ui.common.composables.RadioCard
import com.whiskersapps.clawlauncher.ui.common.composables.ThemeList
import com.whiskersapps.clawlauncher.ui.common.palette.LynxPalettes
import com.whiskersapps.clawlauncher.ui.common.palette.PantherPalettes
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectThemeScreenRoot(
    vm: SelectThemeScreenVM = koinViewModel()
) {
    SelectThemeScreen(
        state = vm.state.collectAsState().value
    ) { action ->
        vm.onAction(action)
    }
}

@Composable
fun SelectThemeScreen(
    state: SelectThemeScreenState,
    onAction: (SelectThemeScreenAction) -> Unit,
) {
    if (!state.loading) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = stringResource(R.string.SelectThemeScreen_title),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.SelectThemeScreen_description),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            RadioCard(
                title = "System",
                selected = state.theme == SettingsValues.Theme.SYSTEM,
                cardShape = CardShape.Top
            ) {
                onAction(SelectThemeScreenAction.ThemeSelect(SettingsValues.Theme.SYSTEM))
            }

            Spacer(modifier = Modifier.height(4.dp))

            RadioCard(
                title = "Light",
                selected = state.theme == SettingsValues.Theme.LIGHT,
                cardShape = CardShape.Middle
            ) {
                onAction(SelectThemeScreenAction.ThemeSelect(SettingsValues.Theme.LIGHT))
            }

            Spacer(modifier = Modifier.height(4.dp))

            RadioCard(
                title = "Dark",
                selected = state.theme == SettingsValues.Theme.DARK,
                cardShape = CardShape.Bottom
            ) {
                onAction(SelectThemeScreenAction.ThemeSelect(SettingsValues.Theme.DARK))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.SelectThemeScreen_light_color_palette_title),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.SelectThemeScreen_light_color_palette_description),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            RadioCard(
                title = "System (Material You)",
                selected = state.palette == SettingsValues.Palette.MONET,
                cardShape = CardShape.Top
            ) {
                onAction(SelectThemeScreenAction.PaletteSelect(SettingsValues.Palette.MONET))
            }

            Spacer(modifier = Modifier.height(4.dp))

            ThemeList(
                title = "Lynx",
                list = LynxPalettes,
                selectedPalette = state.palette,
                cardShape = CardShape.Bottom
            ) { palette ->
                onAction(SelectThemeScreenAction.PaletteSelect(palette))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.SelectThemeScreen_color_palette_title),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.SelectThemeScreen_color_palette_description),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            RadioCard(
                title = "System (Material You)",
                selected = state.darkPalette == SettingsValues.DarkPalette.MONET,
                cardShape = CardShape.Top
            ) {
                onAction(SelectThemeScreenAction.DarkPaletteSelect(SettingsValues.DarkPalette.MONET))
            }

            Spacer(modifier = Modifier.height(4.dp))

            ThemeList(
                title = "Panther",
                list = PantherPalettes,
                selectedPalette = state.darkPalette,
                cardShape = CardShape.Bottom
            ) { palette ->
                onAction(SelectThemeScreenAction.DarkPaletteSelect(palette))
            }
        }
    }
}
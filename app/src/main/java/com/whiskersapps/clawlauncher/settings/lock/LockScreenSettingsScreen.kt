package com.whiskersapps.clawlauncher.settings.lock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.settings.lock.LockScreenSettingsScreenVM.Companion.Action.OnNavigateBack
import com.whiskersapps.clawlauncher.settings.lock.LockScreenSettingsScreenVM.Companion.Action.OnOpenAccessibilitySettings
import com.whiskersapps.clawlauncher.shared.model.Routes
import com.whiskersapps.clawlauncher.shared.view.composables.CenteredLayout
import com.whiskersapps.clawlauncher.shared.view.composables.NavBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun LockScreenSettingsScreenRoot(
    navController: NavController,
    goHome: Boolean,
    vm: LockScreenSettingsScreenVM = koinViewModel()
) {
    LockScreenSettingsScreen(
        vm = vm,
        onAction = { action ->
            when (action) {
                OnNavigateBack -> {
                    if (goHome) {
                        navController.navigate(Routes.Launcher.HOME)
                    } else {
                        navController.navigateUp()
                    }
                }

                else -> {
                    vm.onAction(action)
                }
            }
        }
    )
}

@Composable
fun LockScreenSettingsScreen(
    vm: LockScreenSettingsScreenVM,
    state: LockScreenSettingsScreenVM.Companion.State = vm.state.collectAsState().value,
    onAction: (LockScreenSettingsScreenVM.Companion.Action) -> Unit
) {

    CenteredLayout {
        NavBar(sidePadded = false, navigateBack = { onAction(OnNavigateBack) })

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onAction(OnOpenAccessibilitySettings)
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
            ) {
                Text(
                    text = stringResource(R.string.HomeScreen_accessibility),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = stringResource(R.string.HomeScreen_accessibility_description),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.width(16.dp))

            Icon(
                modifier = Modifier.size(32.dp),
                painter = if (state.accessibilityServiceEnabled) painterResource(R.drawable.check) else painterResource(
                    R.drawable.close
                ),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null
            )
        }
    }
}

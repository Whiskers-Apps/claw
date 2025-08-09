package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.ui.common.composables.getCardShape
import com.whiskersapps.clawlauncher.ui.screens.launcher.LauncherActivity
import com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine.SelectEngineScreenAction.Finish
import com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine.composables.SearchEngineCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectEngineScreenRoot(
    vm: SelectEngineScreenVM = koinViewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity

    SelectEngineScreen(
        vm = vm,
    ) { action ->
        when (action) {
            Finish -> {
                vm.onAction(Finish)

                val intent = Intent(context, LauncherActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                activity?.startActivity(intent)
                activity?.finish()
            }

            else -> {
                vm.onAction(action)
            }
        }
    }
}

@Composable
fun SelectEngineScreen(
    vm: SelectEngineScreenVM,
    onAction: (SelectEngineScreenAction) -> Unit,
) {
    val state = vm.state.collectAsState().value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.SearchEnginesSetupScreen_title),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(R.string.SearchEnginesSetupScreen_description),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(6.dp))
        }

        itemsIndexed(
            items = state.searchEngines,
            key = { index, engine -> engine.id }
        ) { index, searchEngine ->
            SearchEngineCard(
                url = searchEngine.query,
                name = searchEngine.name,
                selected = state.defaultEngine == searchEngine,
                onSelect = { onAction(SelectEngineScreenAction.SetDefaultEngine(searchEngine.id)) },
                cardShape = getCardShape(index, state.searchEngines.size)
            )
        }
    }
}
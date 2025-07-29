package com.whiskersapps.clawlauncher.onboarding.select_engine_screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.launcher.LauncherActivity
import com.whiskersapps.clawlauncher.onboarding.select_engine_screen.SelectEngineScreenAction.Finish
import com.whiskersapps.clawlauncher.onboarding.select_engine_screen.SelectEngineScreenAction.NavigateBack
import com.whiskersapps.clawlauncher.onboarding.select_engine_screen.SelectEngineScreenAction.SetDefaultEngine
import com.whiskersapps.clawlauncher.settings.search_engines.SearchEngineCard
import com.whiskersapps.clawlauncher.shared.view.composables.sidePadding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectEngineScreenRoot(
    pagerState: PagerState,
    vm: SelectEngineScreenVM = koinViewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    SelectEngineScreen(
        vm = vm,
    ) { action ->
        when (action) {
            NavigateBack -> {
                scope.launch(Dispatchers.Main) {
                    pagerState.animateScrollToPage(0)
                }
            }

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
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {

            Column(modifier = Modifier.sidePadding()) {
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

                Spacer(modifier = Modifier.height(16.dp))
            }

        }

        items(
            items = state.searchEngines,
            key = { it.id }
        ) { searchEngine ->
            Box(modifier = Modifier.sidePadding(8.dp)) {
                SearchEngineCard(
                    searchEngine = searchEngine,
                    backgroundColor = if (state.selectedEngine == searchEngine) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.background,
                    onClick = {
                        onAction(SetDefaultEngine(searchEngine.id))
                    }
                )
            }

        }
    }
}
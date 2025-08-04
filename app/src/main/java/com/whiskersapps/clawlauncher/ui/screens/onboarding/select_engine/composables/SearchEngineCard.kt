package com.whiskersapps.clawlauncher.ui.screens.onboarding.select_engine.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.whiskersapps.clawlauncher.R
import com.whiskersapps.clawlauncher.shared.utils.getCachedImageRequest
import com.whiskersapps.clawlauncher.shared.utils.getFaviconUrl
import com.whiskersapps.clawlauncher.ui.common.CardShape
import com.whiskersapps.clawlauncher.ui.common.RadioCard

@Composable
fun SearchEngineCard(
    url: String,
    name: String,
    selected: Boolean,
    onSelect: () -> Unit,
    cardShape: CardShape
) {
    RadioCard(
        icon = {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(42.dp),
                model = getCachedImageRequest(getFaviconUrl(url)),
                contentDescription = null
            ) {
                when (val state = painter.state) {
                    is AsyncImagePainter.State.Success -> {
                        SubcomposeAsyncImageContent()
                    }

                    else -> {
                        Icon(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(48.dp),
                            painter = painterResource(R.drawable.globe),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }

        },
        title = name,
        selected = selected,
        cardShape = cardShape,
        onSelect = { onSelect() }
    )
}
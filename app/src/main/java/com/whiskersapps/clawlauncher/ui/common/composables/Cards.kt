package com.whiskersapps.clawlauncher.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.whiskersapps.clawlauncher.shared.view.theme.REGULAR_LABEL_STYLE

enum class CardShape {
    Top,
    Middle,
    Bottom,
    None
}

@Composable
fun Modifier.cardShape(shape: CardShape): Modifier {
    return when (shape) {
        CardShape.Top -> this.clip(
            RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = 4.dp,
                bottomEnd = 4.dp
            )
        )

        CardShape.Middle -> this.clip(
            RoundedCornerShape(4.dp)
        )

        CardShape.Bottom -> this.clip(
            RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 4.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )
        )

        CardShape.None -> this.clip(RectangleShape)
    }
}

fun getCardShape(index: Int, listSize: Int): CardShape {
    return if (index == 0 && listSize == 1) {
        CardShape.Middle
    } else if (index == 0) {
        CardShape.Top
    } else if (index + 1 == listSize) {
        CardShape.Bottom
    } else {
        CardShape.Middle
    }
}

@Composable
fun RadioCard(
    icon: @Composable (RowScope.() -> Unit)? = null,
    title: String,
    selected: Boolean,
    cardShape: CardShape,
    onSelect: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .cardShape(cardShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onSelect() }
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            icon()

            Spacer(Modifier.width(16.dp))
        }

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = REGULAR_LABEL_STYLE
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        RadioButton(
            selected,
            onClick = { onSelect() })
    }
}
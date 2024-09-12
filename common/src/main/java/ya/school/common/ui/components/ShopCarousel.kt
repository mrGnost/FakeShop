package ya.school.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun <T> ShopCarousel(
    data: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable LazyItemScope.(T) -> Unit
) {
    val listState = rememberLazyListState()
    val itemIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex + 1 }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) {
                content(it)
            }
        }
        PositionIndicator(
            position = itemIndex,
            listSize = data.size,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun PositionIndicator(
    position: Int,
    listSize: Int,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .padding(32.dp)
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = shape
            )
            .padding(8.dp)
    ) {
        Text(
            text = "$position-$listSize",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
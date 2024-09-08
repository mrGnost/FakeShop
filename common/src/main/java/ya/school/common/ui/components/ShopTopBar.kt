package ya.school.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopBar(
    text: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { 
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
    )
}
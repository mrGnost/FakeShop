package ya.school.common.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopTabRow(
    tabIndex: Int,
    tabLabels: List<String>,
    onIndexChange: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = tabIndex,
        divider = { },
        indicator = { }
    ) {
        tabLabels.forEachIndexed { index, label ->
            Tab(
                modifier = Modifier
                    .padding(12.dp),
                selected = tabIndex == index,
                onClick = { onIndexChange(index) },
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}
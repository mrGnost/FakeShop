package ya.school.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ya.school.common.R
import ya.school.common.logic.entity.SortingMethod

@Composable
fun SortingPicker(
    modifier: Modifier = Modifier,
    onSortingMethodChange: (SortingMethod) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = modifier
                .size(64.dp)
                .padding(16.dp)
                .clickable { expanded = !expanded },
            painter = painterResource(id = R.drawable.sort),
            contentDescription = null
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .shadow(elevation = 2.dp)
                .background(MaterialTheme.colorScheme.tertiary)
        ) {
            SortingMethod.entries.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = it.stringId),
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    },
                    onClick = {
                        onSortingMethodChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
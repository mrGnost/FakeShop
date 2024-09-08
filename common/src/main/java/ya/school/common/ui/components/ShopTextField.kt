package ya.school.common.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun ShopTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    hint: String? = null,
    mode: ShopTextFieldMode = ShopTextFieldMode.Normal
) {
    TextField(
        value = text,
        onValueChange = onValueChanged,
        maxLines = 1,
        placeholder = {
            hint?.let {
                Text(text = it)
            }
        }
    )
}

enum class ShopTextFieldMode {
    Normal,
    Password,
    Email
}
package ya.school.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ya.school.common.R
import ya.school.common.logic.util.Constants

@Composable
fun ShopTextField(
    text: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String? = null,
    errorMessage: String? = null,
    mode: ShopTextFieldMode = ShopTextFieldMode.Normal
) {
    var isHidden by remember { mutableStateOf(mode == ShopTextFieldMode.Password) }

    TextField(
        value = text,
        onValueChange = {
            validate(
                text = it,
                mode = mode,
                onSuccess = onValueChanged
            )
        },
        modifier = modifier,
        maxLines = 1,
        placeholder = {
            hint?.let {
                Text(text = it)
            }
        },
        visualTransformation = if (mode == ShopTextFieldMode.Password && isHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if (mode == ShopTextFieldMode.Password) {
                Icon(
                    painter = painterResource(
                        id = when (isHidden) {
                            true -> R.drawable.hidden
                            false -> R.drawable.visible
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            isHidden = !isHidden
                        }
                )
            }
        }
    )
}

private fun validate(
    text: String,
    mode: ShopTextFieldMode,
    onSuccess: (String) -> Unit
) {
    when (mode) {
        ShopTextFieldMode.Password -> {
            if (text.length <= Constants.PASSWORD_MAX_LENGTH) {
                onSuccess(text)
            }
        }

        else -> onSuccess(text)
    }
}

enum class ShopTextFieldMode {
    Normal,
    Password
}
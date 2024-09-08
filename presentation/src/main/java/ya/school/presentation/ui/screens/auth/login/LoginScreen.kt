package ya.school.presentation.ui.screens.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ya.school.common.ui.components.ShopButton
import ya.school.common.ui.components.ShopTextField
import ya.school.common.ui.components.ShopTextFieldMode
import ya.school.presentation.R

@Composable
internal fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ShopTextField(
            text = email,
            onValueChanged = { email = it },
            hint = stringResource(id = R.string.number_or_email),
            modifier = Modifier
                .fillMaxWidth()
        )
        ShopTextField(
            text = password,
            onValueChanged = { password = it },
            hint = stringResource(id = R.string.password),
            modifier = Modifier
                .fillMaxWidth(),
            mode = ShopTextFieldMode.Password
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        ShopButton(
            text = stringResource(id = R.string.enter),
            onClick = { },
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}
package ya.school.presentation.ui.screens.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ya.school.common.ui.components.ShopButton
import ya.school.common.ui.components.ShopLoadingIndicator
import ya.school.common.ui.components.ShopTextField
import ya.school.common.ui.components.ShopTextFieldMode
import ya.school.presentation.R
import ya.school.presentation.ui.screens.auth.login.states.LoginAction
import ya.school.presentation.ui.screens.auth.login.states.LoginEvent
import ya.school.presentation.ui.screens.auth.login.states.LoginScreenState

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val viewState by viewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by viewModel.viewActions().collectAsStateWithLifecycle()

    LaunchedEffect(viewAction) {
        when (viewAction) {
            LoginAction.OpenProductsList -> Unit // TODO: navigation
            is LoginAction.ShowError -> {
                snackbarHostState.showSnackbar(
                    (viewAction as LoginAction.ShowError).message
                )
            }

            else -> Unit
        }
        viewAction?.let {
            viewModel.obtainEvent(LoginEvent.ActionInvoked)
        }
    }

    when (viewState) {
        is LoginScreenState.Data -> Data(
            emailErrorShown = (viewState as LoginScreenState.Data).emailErrorShown,
            onEvent = viewModel::obtainEvent
        )

        LoginScreenState.Loading -> ShopLoadingIndicator()
    }
}

@Composable
private fun Data(
    emailErrorShown: Boolean,
    onEvent: (LoginEvent) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
                .fillMaxWidth(),
            errorMessage = if (emailErrorShown)
                stringResource(id = R.string.incorrect_email)
            else
                null
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
            onClick = {
                onEvent(LoginEvent.LoginButtonPressed(email, password))
            },
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
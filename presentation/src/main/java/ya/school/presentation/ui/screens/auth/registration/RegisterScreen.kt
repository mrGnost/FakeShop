package ya.school.presentation.ui.screens.auth.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.routers.IAuthNavRouter
import ya.school.common.ui.components.ShopButton
import ya.school.common.ui.components.ShopLoadingIndicator
import ya.school.common.ui.components.ShopTextField
import ya.school.common.ui.components.ShopTextFieldMode
import ya.school.presentation.R
import ya.school.presentation.ui.navigation.routers.AuthNavRouter
import ya.school.presentation.ui.screens.auth.registration.states.RegisterAction
import ya.school.presentation.ui.screens.auth.registration.states.RegisterEvent
import ya.school.presentation.ui.screens.auth.registration.states.RegisterScreenState

@Composable
internal fun RegisterScreen(
    router: IAuthNavRouter,
    viewModel: RegisterViewModel = viewModel(),
    onNavEvent: (NavEvent) -> Unit = { }
) {
    val viewState by viewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by viewModel.viewActions().collectAsStateWithLifecycle()

    onNavEvent(
        NavEvent.ChangeTitle(
            stringResource(id = R.string.register_title)
        )
    )

    LaunchedEffect(viewAction) {
        when (viewAction) {
            RegisterAction.OpenLoginScreen -> router.navigateToLogin()
            is RegisterAction.ShowError -> {
                onNavEvent(
                    NavEvent.ShowSnackbar(
                        (viewAction as RegisterAction.ShowError).message
                    )
                )
            }

            else -> Unit
        }
        viewAction?.let {
            viewModel.obtainEvent(RegisterEvent.ActionInvoked)
        }
    }

    when (viewState) {
        is RegisterScreenState.Data -> Data(
            emailErrorShown = (viewState as RegisterScreenState.Data).emailErrorShown,
            onEvent = viewModel::obtainEvent
        )

        RegisterScreenState.Loading -> ShopLoadingIndicator()
    }
}

@Composable
private fun Data(
    emailErrorShown: Boolean,
    onEvent: (RegisterEvent) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatedPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ShopTextField(
            text = name,
            onValueChanged = { name = it },
            hint = stringResource(id = R.string.name),
            modifier = Modifier
                .fillMaxWidth()
        )
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
        ShopTextField(
            text = repeatedPassword,
            onValueChanged = { repeatedPassword = it },
            hint = stringResource(id = R.string.repeat_password),
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
                onEvent(
                    RegisterEvent.EnterButtonPressed(
                        name = name,
                        email = email,
                        password = password,
                        repeatedPassword = repeatedPassword
                    )
                )
            },
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            router = AuthNavRouter(rememberNavController())
        )
    }
}
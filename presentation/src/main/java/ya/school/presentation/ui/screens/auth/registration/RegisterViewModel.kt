package ya.school.presentation.ui.screens.auth.registration

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ya.school.common.logic.entity.DataResult
import ya.school.common.logic.util.EmailUtil
import ya.school.common.logic.viewmodel.BaseSharedViewModel
import ya.school.domain.usecase.api.IRegisterUseCase
import ya.school.presentation.ui.screens.auth.registration.states.RegisterAction
import ya.school.presentation.ui.screens.auth.registration.states.RegisterEvent
import ya.school.presentation.ui.screens.auth.registration.states.RegisterScreenState
import javax.inject.Inject

@HiltViewModel
internal class RegisterViewModel @Inject constructor(
    private val registerUseCase: IRegisterUseCase
) : BaseSharedViewModel<RegisterScreenState, RegisterAction, RegisterEvent>(
    initialState = RegisterScreenState.Default
) {
    private val currentScreenState = MutableStateFlow(RegisterScreenState.Default)

    init {
        subscribeOnScreenUpdates()
    }

    private fun subscribeOnScreenUpdates() {
        withViewModelScope {
            currentScreenState.collect {
                viewState = it
            }
        }
    }

    override fun obtainEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            RegisterEvent.ActionInvoked -> viewAction = null
            is RegisterEvent.EnterButtonPressed -> registerUser(
                name = viewEvent.name,
                email = viewEvent.email,
                password = viewEvent.password,
                repeatedPassword = viewEvent.repeatedPassword
            )
        }
    }

    private fun registerUser(
        name: String,
        email: String,
        password: String,
        repeatedPassword: String
    ) {
        val trimmedEmail = email.trim()
        if (!EmailUtil.isValid(trimmedEmail)) {
            currentScreenState.update {
                it.copy(emailErrorShown = true)
            }
            return
        }
        withViewModelScope {
            registerUseCase(name, email, password, repeatedPassword).let { result ->
                viewAction = when (result) {
                    is DataResult.Error -> RegisterAction.ShowError(result.message)
                    is DataResult.Success -> RegisterAction.OpenLoginScreen
                }
            }
        }
    }
}
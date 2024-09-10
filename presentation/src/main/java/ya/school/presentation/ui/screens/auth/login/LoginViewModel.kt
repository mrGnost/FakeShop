package ya.school.presentation.ui.screens.auth.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ya.school.common.logic.entity.DataResult
import ya.school.common.logic.util.EmailUtil
import ya.school.common.logic.viewmodel.BaseSharedViewModel
import ya.school.domain.usecase.api.IAuthorizeUseCase
import ya.school.presentation.ui.screens.auth.login.states.LoginAction
import ya.school.presentation.ui.screens.auth.login.states.LoginEvent
import ya.school.presentation.ui.screens.auth.login.states.LoginScreenState
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val authorizeUseCase: IAuthorizeUseCase
) : BaseSharedViewModel<LoginScreenState, LoginAction, LoginEvent>(
    initialState = LoginScreenState.Default
) {
    private val currentScreenState = MutableStateFlow(LoginScreenState.Default)

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

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.LoginButtonPressed -> authorize(viewEvent.email, viewEvent.password)
            is LoginEvent.ActionInvoked -> viewAction = null
        }
    }

    private fun authorize(email: String, password: String) {
        if (!EmailUtil.isValid(email)) {
            currentScreenState.update {
                it.copy(emailErrorShown = true)
            }
            return
        }
        withViewModelScope {
            authorizeUseCase(email, password).let { result ->
                viewAction = when (result) {
                    is DataResult.Error -> LoginAction.ShowError(result.message)
                    is DataResult.Success -> LoginAction.OpenProductsList
                }
            }
        }
    }
}
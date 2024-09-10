package ya.school.presentation.ui.screens.auth.login.states

sealed class LoginAction {
    data class ShowError(val message: String) : LoginAction()
    data object OpenProductsList : LoginAction()
}
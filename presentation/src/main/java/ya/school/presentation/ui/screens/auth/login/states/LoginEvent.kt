package ya.school.presentation.ui.screens.auth.login.states

sealed class LoginEvent {
    data class LoginButtonPressed(
        val email: String,
        val password: String
    ) : LoginEvent()
    data object ActionInvoked : LoginEvent()
}
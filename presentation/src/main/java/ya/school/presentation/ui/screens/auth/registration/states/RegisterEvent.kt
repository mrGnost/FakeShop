package ya.school.presentation.ui.screens.auth.registration.states

internal sealed class RegisterEvent {
    data class EnterButtonPressed(
        val name: String,
        val email: String,
        val password: String,
        val repeatedPassword: String
    ) : RegisterEvent()

    data object ActionInvoked : RegisterEvent()
}
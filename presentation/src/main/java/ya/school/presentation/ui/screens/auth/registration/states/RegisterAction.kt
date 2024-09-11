package ya.school.presentation.ui.screens.auth.registration.states

internal sealed class RegisterAction {
    data class ShowError(val message: String) : RegisterAction()
    data object OpenLoginScreen : RegisterAction()
}
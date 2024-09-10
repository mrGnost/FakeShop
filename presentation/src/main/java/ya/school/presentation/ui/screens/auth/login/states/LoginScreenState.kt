package ya.school.presentation.ui.screens.auth.login.states

internal sealed class LoginScreenState {
    data class Data(
        val emailErrorShown: Boolean
    ) : LoginScreenState()

    data object Loading : LoginScreenState()

    companion object {
        val Default = Data(false)
    }
}
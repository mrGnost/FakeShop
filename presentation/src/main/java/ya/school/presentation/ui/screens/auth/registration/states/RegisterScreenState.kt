package ya.school.presentation.ui.screens.auth.registration.states

internal sealed class RegisterScreenState {
    data class Data(
        val emailErrorShown: Boolean
    ) : RegisterScreenState()

    data object Loading : RegisterScreenState()

    companion object {
        val Default = Data(false)
    }
}
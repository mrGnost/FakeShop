package ya.school.common.logic.navigation

sealed class NavEvent {
    data class ChangeTitle(val title: String) : NavEvent()
    data class ShowSnackbar(val snackbar: String) : NavEvent()
    data object NavigateToProducts : NavEvent()
}
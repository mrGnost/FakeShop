package ya.school.common.logic.navigation

sealed class NavEvent {
    data class ChangeTitle(val title: String) : NavEvent()
}
package ya.school.presentation.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ya.school.common.logic.navigation.LoginRoute
import ya.school.common.logic.navigation.NavEvent
import ya.school.presentation.ui.navigation.loginDestination
import ya.school.presentation.ui.navigation.registerDestination
import ya.school.presentation.ui.navigation.routers.AuthNavRouter

@Composable
internal fun AuthHostScreen(
    onNavEvent: (NavEvent) -> Unit
) {
    val controller = rememberNavController()
    val router = AuthNavRouter(controller)

    NavHost(navController = controller, startDestination = LoginRoute) {
        registerDestination()
        loginDestination(
            onNavEvent = onNavEvent
        )
    }
}
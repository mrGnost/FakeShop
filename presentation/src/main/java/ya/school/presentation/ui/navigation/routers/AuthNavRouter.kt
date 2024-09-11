package ya.school.presentation.ui.navigation.routers

import androidx.navigation.NavHostController
import ya.school.common.logic.navigation.LoginRoute
import ya.school.common.logic.navigation.routers.IAuthNavRouter

class AuthNavRouter(
    val controller: NavHostController
) : IAuthNavRouter {
    override fun navigateToLogin() {
        controller.navigate(LoginRoute)
    }
}
package ya.school.presentation.ui.navigation.routers

import androidx.navigation.NavHostController
import ya.school.common.logic.navigation.AuthNavigationRoute
import ya.school.common.logic.navigation.ProductsNavigationRoute
import ya.school.common.logic.navigation.routers.IMainNavRouter

class MainNavRouter(
    val controller: NavHostController
) : IMainNavRouter {
    override fun navigateToProducts() {
        controller.navigate(ProductsNavigationRoute) {
            popUpTo<AuthNavigationRoute> {
                inclusive = true
            }
        }
    }
}
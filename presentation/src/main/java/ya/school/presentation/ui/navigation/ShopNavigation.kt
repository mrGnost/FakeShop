package ya.school.presentation.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ya.school.common.logic.navigation.AuthHostRoute
import ya.school.common.logic.navigation.AuthNavigationRoute
import ya.school.common.logic.navigation.LoginRoute
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.ProductInfoRoute
import ya.school.common.logic.navigation.ProductsHostRoute
import ya.school.common.logic.navigation.ProductsListRoute
import ya.school.common.logic.navigation.ProductsNavigationRoute
import ya.school.common.logic.navigation.RegisterRoute
import ya.school.presentation.ui.screens.auth.AuthHostScreen
import ya.school.presentation.ui.screens.auth.login.LoginScreen
import ya.school.presentation.ui.screens.products.ProductsHostScreen

internal fun NavGraphBuilder.authNavigation(
    content: NavGraphBuilder.() -> Unit
) {
    navigation<AuthNavigationRoute>(startDestination = AuthHostRoute) {
        content()
    }
}

internal fun NavGraphBuilder.authHostDestination(
    onNavEvent: (NavEvent) -> Unit
) {
    composable<AuthHostRoute> {
        AuthHostScreen(
            onNavEvent = onNavEvent
        )
    }
}

internal fun NavGraphBuilder.registerDestination() {
    composable<RegisterRoute> {

    }
}

internal fun NavGraphBuilder.loginDestination(
    onNavEvent: (NavEvent) -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(
            viewModel = hiltViewModel(),
            onNavEvent = onNavEvent
        )
    }
}

internal fun NavGraphBuilder.productsNavigation(
    content: NavGraphBuilder.() -> Unit
) {
    navigation<ProductsNavigationRoute>(startDestination = ProductsHostRoute) {
        content()
    }
}

internal fun NavGraphBuilder.productsHostDestination(
    onNavEvent: (NavEvent) -> Unit
) {
    composable<ProductsHostRoute> {
        ProductsHostScreen(
            onNavEvent = onNavEvent
        )
    }
}

internal fun NavGraphBuilder.productsListDestination() {
    composable<ProductsListRoute> {

    }
}

internal fun NavGraphBuilder.productInfoDestination() {
    composable<ProductInfoRoute> {

    }
}
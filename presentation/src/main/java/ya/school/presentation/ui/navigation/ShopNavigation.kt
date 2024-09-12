package ya.school.presentation.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import ya.school.common.logic.navigation.AuthHostRoute
import ya.school.common.logic.navigation.AuthNavigationRoute
import ya.school.common.logic.navigation.LoginRoute
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.ProductInfoRoute
import ya.school.common.logic.navigation.ProductsHostRoute
import ya.school.common.logic.navigation.ProductsListRoute
import ya.school.common.logic.navigation.ProductsNavigationRoute
import ya.school.common.logic.navigation.RegisterRoute
import ya.school.common.logic.navigation.routers.IAuthNavRouter
import ya.school.common.logic.navigation.routers.IProductsNavRouter
import ya.school.presentation.ui.screens.auth.AuthHostScreen
import ya.school.presentation.ui.screens.auth.login.LoginScreen
import ya.school.presentation.ui.screens.auth.registration.RegisterScreen
import ya.school.presentation.ui.screens.products.ProductsHostScreen
import ya.school.presentation.ui.screens.products.productInfo.ProductInfoScreen
import ya.school.presentation.ui.screens.products.productsList.ProductsListScreen

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

internal fun NavGraphBuilder.registerDestination(
    router: IAuthNavRouter,
    onNavEvent: (NavEvent) -> Unit
) {
    composable<RegisterRoute> {
        RegisterScreen(
            router = router,
            viewModel = hiltViewModel(),
            onNavEvent = onNavEvent
        )
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

internal fun NavGraphBuilder.productsListDestination(
    router: IProductsNavRouter,
    onNavEvent: (NavEvent) -> Unit
) {
    composable<ProductsListRoute> {
        ProductsListScreen(
            router = router,
            viewModel = hiltViewModel(),
            onNavEvent = onNavEvent
        )
    }
}

internal fun NavGraphBuilder.productInfoDestination(
    router: IProductsNavRouter,
    onNavEvent: (NavEvent) -> Unit
) {
    composable<ProductInfoRoute> {
        ProductInfoScreen(
            productId = it.toRoute<ProductInfoRoute>().id,
            router = router,
            viewModel = hiltViewModel(),
            onNavEvent = onNavEvent
        )
    }
}
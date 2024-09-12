package ya.school.presentation.ui.screens.products

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.ProductsListRoute
import ya.school.presentation.ui.navigation.productInfoDestination
import ya.school.presentation.ui.navigation.productsListDestination
import ya.school.presentation.ui.navigation.routers.ProductsNavRouter

@Composable
internal fun ProductsHostScreen(
    onNavEvent: (NavEvent) -> Unit
) {
    val controller = rememberNavController()
    val router = ProductsNavRouter(controller)

    NavHost(navController = controller, startDestination = ProductsListRoute) {
        productsListDestination(
            router = router,
            onNavEvent = onNavEvent
        )
        productInfoDestination()
    }
}
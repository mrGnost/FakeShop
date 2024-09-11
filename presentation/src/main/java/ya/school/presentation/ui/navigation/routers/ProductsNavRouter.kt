package ya.school.presentation.ui.navigation.routers

import androidx.navigation.NavHostController
import ya.school.common.logic.navigation.ProductInfoRoute
import ya.school.common.logic.navigation.ProductsListRoute
import ya.school.common.logic.navigation.routers.IProductsNavRouter

class ProductsNavRouter(
    val controller: NavHostController
) : IProductsNavRouter {
    override fun navigateToProductInfo(id: String) {
        controller.navigate(ProductInfoRoute(id))
    }

    override fun navigateBack() {
        controller.popBackStack(
            route = ProductsListRoute,
            inclusive = false
        )
    }
}
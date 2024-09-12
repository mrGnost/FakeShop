package ya.school.presentation.ui.screens.products.productsList.states

internal sealed class ProductsListEvent {
    data object ActionInvoked : ProductsListEvent()

    data class ProductClicked(
        val productId: String
    ) : ProductsListEvent()

    data class CategoryClicked(
        val category: String
    ) : ProductsListEvent()

    data class TabClicked(
        val tabIndex: Int
    ) : ProductsListEvent()
}
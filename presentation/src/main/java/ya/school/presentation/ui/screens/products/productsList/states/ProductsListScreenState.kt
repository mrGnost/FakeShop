package ya.school.presentation.ui.screens.products.productsList.states

import ya.school.domain.entity.ProductShort

internal sealed class ProductsListScreenState {
    data class Data(
        val products: List<ProductShort>,
        val selectedTabIndex: Int
    ) : ProductsListScreenState()

    data object Loading : ProductsListScreenState()

    companion object {
        val Default = Data(emptyList(), 0)
    }
}
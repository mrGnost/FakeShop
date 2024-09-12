package ya.school.presentation.ui.screens.products.productsList.states

import ya.school.common.logic.entity.SortingMethod

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

    data class PagingLimitChanged(
        val limit: Int
    ) : ProductsListEvent()

    data object RetryInvoked : ProductsListEvent()

    data class SortingMethodChanged(
        val sortingMethod: SortingMethod
    ) : ProductsListEvent()
}
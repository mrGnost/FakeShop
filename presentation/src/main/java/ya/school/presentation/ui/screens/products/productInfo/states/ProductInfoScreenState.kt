package ya.school.presentation.ui.screens.products.productInfo.states

import ya.school.domain.entity.ProductFull

internal sealed class ProductInfoScreenState {
    data class Data(
        val product: ProductFull
    ) : ProductInfoScreenState()

    data object Loading : ProductInfoScreenState()

    data object Error : ProductInfoScreenState()

    companion object {
        val Default = Data(ProductFull.Empty)
    }
}
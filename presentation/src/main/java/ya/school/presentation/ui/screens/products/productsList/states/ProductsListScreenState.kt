package ya.school.presentation.ui.screens.products.productsList.states

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ya.school.domain.entity.ProductShort

internal sealed class ProductsListScreenState {
    data class Data(
        val products: Flow<PagingData<ProductShort>>?,
        val selectedTabIndex: Int
    ) : ProductsListScreenState()

    data object Loading : ProductsListScreenState()

    data object Error : ProductsListScreenState()

    companion object {
        val Default = Data(null, 0)
    }
}
package ya.school.presentation.ui.screens.products.productsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.Flow
import ya.school.common.logic.entity.Category
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.routers.IProductsNavRouter
import ya.school.common.ui.components.CategoryCard
import ya.school.common.ui.components.ProductCard
import ya.school.common.ui.components.ShopLoadingIndicator
import ya.school.common.ui.components.ShopTabRow
import ya.school.domain.entity.ProductShort
import ya.school.presentation.R
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListAction
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListEvent
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListScreenState

@Composable
internal fun ProductsListScreen(
    router: IProductsNavRouter,
    viewModel: ProductsListViewModel = viewModel(),
    onNavEvent: (NavEvent) -> Unit
) {
    val viewState by viewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by viewModel.viewActions().collectAsStateWithLifecycle()

    onNavEvent(
        NavEvent.ChangeTitle(null)
    )

    LaunchedEffect(viewAction) {
        when (viewAction) {
            is ProductsListAction.OpenProductInfo -> {
                router.navigateToProductInfo((viewAction as ProductsListAction.OpenProductInfo).id)
            }

            is ProductsListAction.ShowError -> onNavEvent(
                NavEvent.ShowSnackbar((viewAction as ProductsListAction.ShowError).message)
            )

            null -> Unit
        }
        viewAction?.let {
            viewModel.obtainEvent(ProductsListEvent.ActionInvoked)
        }
    }

    when (viewState) {
        is ProductsListScreenState.Data -> Data(
            products = (viewState as ProductsListScreenState.Data).products,
            selectedTabIndex = (viewState as ProductsListScreenState.Data).selectedTabIndex,
            onEvent = { viewModel.obtainEvent(it) }
        )

        ProductsListScreenState.Loading -> ShopLoadingIndicator()
    }
}

@Composable
private fun Data(
    products: Flow<PagingData<ProductShort>>? = null,
    selectedTabIndex: Int,
    onEvent: (ProductsListEvent) -> Unit
) {
    val productItems = products?.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .padding(top = 32.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            CategoriesList(onEvent = onEvent)
        }
        item(span = { GridItemSpan(2) }) {
            ShopTabRow(
                tabIndex = selectedTabIndex,
                tabLabels = listOf(
                    stringResource(id = R.string.recommendations),
                    stringResource(id = R.string.fresh),
                    stringResource(id = R.string.nearby)
                ),
                onIndexChange = { onEvent(ProductsListEvent.TabClicked(it)) }
            )
        }
        productItems?.let { products ->
            items(
                products.itemCount,
                key = products.itemKey { it.id },
                span = { GridItemSpan(1) }
            ) { index ->
                products[index]?.run {
                    ProductCard(
                        imagePath = imageUrl,
                        name = name,
                        price = price,
                        priceDiscounted = priceDiscounted,
                        onClick = { onEvent(ProductsListEvent.ProductClicked(id)) }
                    )
                }
            }
        }
    }

}

@Composable
private fun CategoriesList(
    onEvent: (ProductsListEvent) -> Unit
) {
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalItemSpacing = 8.dp,
        modifier = Modifier
            .height(256.dp)
    ) {
        items(Category.entries) {
            CategoryCard(
                category = it,
                onClick = { onEvent(ProductsListEvent.CategoryClicked(it.code)) }
            )
        }
    }
}
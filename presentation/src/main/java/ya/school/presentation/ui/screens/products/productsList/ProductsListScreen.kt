package ya.school.presentation.ui.screens.products.productsList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ya.school.common.ui.components.ShopErrorScreen
import ya.school.common.ui.components.ShopLoadingIndicator
import ya.school.common.ui.components.ShopTabRow
import ya.school.common.ui.components.SortingPicker
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
        ProductsListScreenState.Error -> ShopErrorScreen {
            viewModel.obtainEvent(ProductsListEvent.RetryInvoked)
        }
    }
}

@Composable
private fun Data(
    products: Flow<PagingData<ProductShort>>? = null,
    selectedTabIndex: Int,
    onEvent: (ProductsListEvent) -> Unit
) {
    val productItems = products?.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SortingPicker(
            modifier = Modifier
                .padding(16.dp)
        ) {
            onEvent(ProductsListEvent.SortingMethodChanged(it))
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .weight(1f)
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
                            onClick = { onEvent(ProductsListEvent.ProductClicked(id)) },
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
        ItemsPerPagePicker {
            onEvent(ProductsListEvent.PagingLimitChanged(it))
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
        contentPadding = PaddingValues(16.dp),
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

@Composable
private fun ItemsPerPagePicker(
    onPick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemsPerPageButton(text = stringResource(id = R.string.show_with_count)) { }
        Spacer(modifier = Modifier.weight(1f))
        ItemsPerPageButton("10") {
            onPick(10)
        }
        ItemsPerPageButton("20") {
            onPick(20)
        }
        ItemsPerPageButton("30") {
            onPick(30)
        }
    }
}

@Composable
private fun ItemsPerPageButton(
    text: String,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = shape
            )
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}
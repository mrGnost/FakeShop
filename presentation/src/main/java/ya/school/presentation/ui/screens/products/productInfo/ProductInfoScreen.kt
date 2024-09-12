package ya.school.presentation.ui.screens.products.productInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.logic.navigation.routers.IProductsNavRouter
import ya.school.common.ui.components.PriceBlock
import ya.school.common.ui.components.ShopButton
import ya.school.common.ui.components.ShopCarousel
import ya.school.common.ui.components.ShopErrorScreen
import ya.school.common.ui.components.ShopImage
import ya.school.common.ui.components.ShopLoadingIndicator
import ya.school.domain.entity.ProductFull
import ya.school.presentation.R
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoAction
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoEvent
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoScreenState

@Composable
internal fun ProductInfoScreen(
    productId: String,
    router: IProductsNavRouter,
    viewModel: ProductInfoViewModel = viewModel(),
    onNavEvent: (NavEvent) -> Unit
) {
    val viewState by viewModel.viewStates().collectAsStateWithLifecycle()
    val viewAction by viewModel.viewActions().collectAsStateWithLifecycle()

    onNavEvent(
        NavEvent.ChangeTitle(null)
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(ProductInfoEvent.LoadProduct(productId))
    }

    LaunchedEffect(viewAction) {
        when (viewAction) {
            ProductInfoAction.NavigateBack -> router.navigateBack()
            null -> Unit
        }
        viewAction?.let {
            viewModel.obtainEvent(ProductInfoEvent.ActionInvoked)
        }
    }

    when (viewState) {
        is ProductInfoScreenState.Data -> Data(
            product = (viewState as ProductInfoScreenState.Data).product,
            onEvent = { viewModel.obtainEvent(it) }
        )

        ProductInfoScreenState.Error -> ShopErrorScreen {
            viewModel.obtainEvent(ProductInfoEvent.LoadProduct(productId))
        }

        ProductInfoScreenState.Loading -> ShopLoadingIndicator()
    }
}

@Composable
private fun Data(
    product: ProductFull,
    onEvent: (ProductInfoEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (product.imagesUrl.isNotEmpty()) {
            ShopCarousel(
                data = product.imagesUrl,
                modifier = Modifier
                    .height(256.dp)
            ) {
                ShopImage(imagePath = it)
            }
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            PriceBlock(
                price = product.price,
                priceDiscounted = product.priceDiscounted
            )
            Text(
                text = product.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
            Text(text = product.description)
            Spacer(modifier = Modifier.weight(1f))
            ShopButton(
                text = stringResource(id = R.string.buy),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onEvent(ProductInfoEvent.BuyProduct)
                }
            )
        }
    }
}
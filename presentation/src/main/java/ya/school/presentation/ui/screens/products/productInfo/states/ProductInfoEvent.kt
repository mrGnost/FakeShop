package ya.school.presentation.ui.screens.products.productInfo.states

internal sealed class ProductInfoEvent {
    data object ActionInvoked : ProductInfoEvent()

    data class LoadProduct(
        val id: String
    ) : ProductInfoEvent()

    data object BuyProduct : ProductInfoEvent()
}
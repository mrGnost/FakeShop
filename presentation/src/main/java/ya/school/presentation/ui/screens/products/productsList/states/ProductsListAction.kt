package ya.school.presentation.ui.screens.products.productsList.states

internal sealed class ProductsListAction {
    data class ShowError(val message: String) : ProductsListAction()
    data class OpenProductInfo(val id: String) : ProductsListAction()
}
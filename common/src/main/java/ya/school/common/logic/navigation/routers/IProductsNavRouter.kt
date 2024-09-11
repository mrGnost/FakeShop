package ya.school.common.logic.navigation.routers

interface IProductsNavRouter {
    fun navigateToProductInfo(id: String)
    fun navigateBack()
}
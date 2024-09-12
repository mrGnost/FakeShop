package ya.school.presentation.ui.screens.products.productsList

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ya.school.common.logic.entity.DataResult
import ya.school.common.logic.viewmodel.BaseSharedViewModel
import ya.school.domain.usecase.api.IGetProductsUseCase
import ya.school.presentation.ui.screens.auth.login.states.LoginScreenState
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListAction
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListEvent
import ya.school.presentation.ui.screens.products.productsList.states.ProductsListScreenState
import javax.inject.Inject

@HiltViewModel
internal class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: IGetProductsUseCase
) : BaseSharedViewModel<ProductsListScreenState, ProductsListAction, ProductsListEvent>(
    initialState = ProductsListScreenState.Loading
) {
    private val currentScreenState = MutableStateFlow(ProductsListScreenState.Default)

    init {
        subscribeOnScreenUpdates()
        loadData()
    }

    private fun subscribeOnScreenUpdates() {
        withViewModelScope {
            currentScreenState.collect {
                viewState = it
            }
        }
    }

    override fun obtainEvent(viewEvent: ProductsListEvent) {
        when (viewEvent) {
            ProductsListEvent.ActionInvoked -> viewAction = null
            is ProductsListEvent.CategoryClicked -> loadData(viewEvent.category)
            is ProductsListEvent.ProductClicked -> {
                viewAction = ProductsListAction.OpenProductInfo(viewEvent.productId)
            }

            is ProductsListEvent.TabClicked -> switchTab(viewEvent.tabIndex)
        }
    }

    private fun loadData(category: String? = null) {
        withViewModelScope {
            getProductsUseCase(
                category = category
            ).let { result ->
                when (result) {
                    is DataResult.Error -> viewAction = ProductsListAction.ShowError(result.message)
                    is DataResult.Success -> {
                        currentScreenState.update {
                            it.copy(products = result.data)
                        }
                    }
                }
            }
        }
    }

    private fun switchTab(tabIndex: Int) {
        currentScreenState.update {
            it.copy(selectedTabIndex = tabIndex)
        }
        loadData()
    }
}
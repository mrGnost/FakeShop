package ya.school.presentation.ui.screens.products.productsList

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import ya.school.common.logic.entity.SortingMethod
import ya.school.common.logic.viewmodel.BaseSharedViewModel
import ya.school.domain.usecase.api.IGetProductsUseCase
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
    private val pickedLimit = MutableStateFlow(20)
    private val pickedSortingMethod = MutableStateFlow(SortingMethod.None)

    init {
        subscribeOnScreenUpdates()
        loadData()
        subscribeOnSortingPropertiesUpdates()
    }

    private fun subscribeOnScreenUpdates() {
        withViewModelScope {
            currentScreenState.collect {
                viewState = it
            }
        }
    }

    private fun subscribeOnSortingPropertiesUpdates() {
        withViewModelScope {
            combine(pickedLimit, pickedSortingMethod) { limit, sortingMethod ->
                limit to sortingMethod
            }.collect {
                loadData(
                    limit = it.first,
                    sortingMethod = it.second
                )
            }
        }
    }

    override fun obtainEvent(viewEvent: ProductsListEvent) {
        when (viewEvent) {
            ProductsListEvent.ActionInvoked -> viewAction = null
            is ProductsListEvent.CategoryClicked -> loadData(
                viewEvent.category,
                pickedLimit.value
            )

            is ProductsListEvent.ProductClicked -> {
                viewAction = ProductsListAction.OpenProductInfo(viewEvent.productId)
            }

            is ProductsListEvent.TabClicked -> switchTab(viewEvent.tabIndex)
            is ProductsListEvent.PagingLimitChanged -> pickedLimit.update { viewEvent.limit }

            ProductsListEvent.RetryInvoked -> loadData(limit = pickedLimit.value)
            is ProductsListEvent.SortingMethodChanged -> pickedSortingMethod.update { viewEvent.sortingMethod }
        }
    }

    private fun loadData(
        category: String? = null,
        limit: Int = 20,
        sortingMethod: SortingMethod = SortingMethod.None
    ) {
        viewState = ProductsListScreenState.Loading
        withViewModelScope {
            getProductsUseCase(
                category = category,
                limit = limit,
                sort = sortingMethod.request
            ).cachedIn(viewModelScope).let { flow ->
                currentScreenState.update {
                    it.copy(products = flow)
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
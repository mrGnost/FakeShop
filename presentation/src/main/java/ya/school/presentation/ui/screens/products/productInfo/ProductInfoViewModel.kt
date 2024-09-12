package ya.school.presentation.ui.screens.products.productInfo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ya.school.common.logic.entity.DataResult
import ya.school.common.logic.viewmodel.BaseSharedViewModel
import ya.school.domain.usecase.GetProductInfoUseCase
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoAction
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoEvent
import ya.school.presentation.ui.screens.products.productInfo.states.ProductInfoScreenState
import javax.inject.Inject

@HiltViewModel
internal class ProductInfoViewModel @Inject constructor(
    private val getProductInfoUseCase: GetProductInfoUseCase
) : BaseSharedViewModel<ProductInfoScreenState, ProductInfoAction, ProductInfoEvent>(
    initialState = ProductInfoScreenState.Loading
) {
    private val currentScreenState = MutableStateFlow(ProductInfoScreenState.Default)

    init {
        subscribeOnScreenUpdates()
    }

    private fun subscribeOnScreenUpdates() {
        withViewModelScope {
            currentScreenState.collect {
                viewState = it
            }
        }
    }

    override fun obtainEvent(viewEvent: ProductInfoEvent) {
        when (viewEvent) {
            ProductInfoEvent.ActionInvoked -> viewAction = null
            is ProductInfoEvent.LoadProduct -> loadData(viewEvent.id)
            ProductInfoEvent.BuyProduct -> viewAction = ProductInfoAction.NavigateBack
        }
    }

    private fun loadData(id: String) {
        viewState = ProductInfoScreenState.Loading
        withViewModelScope {
            getProductInfoUseCase(id).let { result ->
                when (result) {
                    is DataResult.Error -> viewState = ProductInfoScreenState.Error
                    is DataResult.Success -> currentScreenState.update {
                        it.copy(product = result.data)
                    }
                }
            }
        }
    }
}
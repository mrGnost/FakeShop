package ya.school.common.logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseSharedViewModel<State : Any, Action, Event>(initialState: State) : ViewModel() {
    private val _viewState = MutableStateFlow(initialState)
    protected var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.update { value }
        }

    private val _viewAction = MutableStateFlow<Action?>(null)
    protected var viewAction: Action?
        get() = _viewAction.value
        set(value) {
            _viewAction.update { value }
        }

    fun viewStates(): StateFlow<State> = _viewState

    fun viewActions(): StateFlow<Action?> = _viewAction

    abstract fun obtainEvent(viewEvent: Event)

    protected fun withViewModelScope(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(
        context = context,
        block = block
    )
}
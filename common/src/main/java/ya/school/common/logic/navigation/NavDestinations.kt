package ya.school.common.logic.navigation

import kotlinx.serialization.Serializable

@Serializable data object AuthNavigationRoute
@Serializable data object AuthHostRoute
@Serializable data object RegisterRoute
@Serializable data object LoginRoute
@Serializable data object ProductsNavigationRoute
@Serializable data object ProductsHostRoute
@Serializable data object ProductsListRoute
@Serializable data class ProductInfoRoute(val id: String)
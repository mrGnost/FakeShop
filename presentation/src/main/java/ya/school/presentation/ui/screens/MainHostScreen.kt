package ya.school.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ya.school.common.logic.navigation.AuthNavigationRoute
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.ui.components.ShopTopBar
import ya.school.presentation.ui.navigation.authHostDestination
import ya.school.presentation.ui.navigation.authNavigation
import ya.school.presentation.ui.navigation.productsHostDestination
import ya.school.presentation.ui.navigation.productsNavigation
import ya.school.presentation.ui.navigation.routers.MainNavRouter

@Composable
internal fun MainHostScreen() {
    val controller = rememberNavController()
    val router = MainNavRouter(controller)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var title by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            if (title.isNotBlank()) {
                ShopTopBar(text = title)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) { innerPadding ->
        MainNavGraph(
            router = router,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { event ->
            when (event) {
                is NavEvent.ChangeTitle -> title = event.title
                NavEvent.NavigateToProducts -> router.navigateToProducts()
                is NavEvent.ShowSnackbar -> scope.launch {
                    snackbarHostState.showSnackbar(event.snackbar)
                }
            }
        }
    }
}

@Composable
private fun MainNavGraph(
    router: MainNavRouter,
    modifier: Modifier = Modifier,
    onNavEvent: (NavEvent) -> Unit
) {
    NavHost(
        navController = router.controller,
        startDestination = AuthNavigationRoute,
        modifier = modifier
    ) {
        authNavigation {
            authHostDestination(
                onNavEvent = onNavEvent
            )
        }
        productsNavigation {
            productsHostDestination(
                onNavEvent = onNavEvent
            )
        }
    }
}
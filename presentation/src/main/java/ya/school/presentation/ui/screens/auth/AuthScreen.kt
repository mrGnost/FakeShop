package ya.school.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ya.school.common.logic.navigation.NavEvent
import ya.school.common.ui.components.ShopTopBar
import ya.school.presentation.R
import ya.school.presentation.ui.screens.auth.login.LoginScreen

@Composable
internal fun AuthScreen() {
    var title by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ShopTopBar(text = stringResource(id = R.string.enter))
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onNavEvent = { event ->
                    when (event) {
                        is NavEvent.ChangeTitle -> title = event.title
                    }
                }
            )
        }
    }
}
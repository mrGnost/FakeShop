package ya.school.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = Blue,
    tertiary = Gray,
    background = Black,
    surface = Black,
    onPrimary = White,
    onSurface = White,
    onSurfaceVariant = GrayText,
    surfaceContainer = GrayBG
)

@Composable
fun FakeShopTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
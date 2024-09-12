package ya.school.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import ya.school.common.logic.util.PriceUtil

@Composable
fun PriceBlock(
    price: Int,
    priceDiscounted: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = PriceUtil.priceToString(priceDiscounted),
            fontSize = 24.sp
        )
        if (price != priceDiscounted) {
            Text(
                text = PriceUtil.priceToString(price),
                fontSize = 16.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
        }
    }
}
package ya.school.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import ya.school.common.logic.util.PriceUtil

@Composable
fun ProductCard(
    imagePath: String,
    name: String,
    price: Int,
    priceDiscounted: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        ShopImage(
            imagePath = imagePath
        )
        Text(text = name)
        Text(text = PriceUtil.priceToString(priceDiscounted))
        if (price != priceDiscounted) {
            Text(
                text = PriceUtil.priceToString(price),
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
        }
    }
}
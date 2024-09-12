package ya.school.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ya.school.common.logic.util.PriceUtil

@Composable
fun ProductCard(
    name: String,
    price: Int,
    priceDiscounted: Int,
    modifier: Modifier = Modifier,
    imagePath: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ShopImage(
            imagePath = imagePath
        )
        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            lineHeight = 16.sp
        )
        PriceBlock(
            price = price,
            priceDiscounted = priceDiscounted
        )
    }
}
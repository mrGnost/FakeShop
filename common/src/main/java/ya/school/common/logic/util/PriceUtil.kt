package ya.school.common.logic.util

import java.text.NumberFormat
import java.util.Currency

object PriceUtil {
    private val formatter = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance("RUB")
    }

    fun priceToString(price: Int): String = formatter.format(price)
}
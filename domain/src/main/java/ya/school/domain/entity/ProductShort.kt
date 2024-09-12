package ya.school.domain.entity

data class ProductShort(
    val id: String,
    val imageUrl: String? = null,
    val name: String,
    val price: Int,
    val priceDiscounted: Int
)

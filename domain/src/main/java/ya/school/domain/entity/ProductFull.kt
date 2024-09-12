package ya.school.domain.entity

data class ProductFull(
    val id: String,
    val imagesUrl: List<String>,
    val price: Int,
    val priceDiscounted: Int,
    val name: String,
    val description: String
) {
    companion object {
        val Empty = ProductFull("", listOf(), 0, 0, "", "")
    }
}

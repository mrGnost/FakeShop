package ya.school.data.model

import com.google.gson.annotations.SerializedName

data class ProductShortDTO(
    @SerializedName("_id") val id: String,
    val images: List<String>,
    val name: String,
    val price: Int,
    @SerializedName("discounted_price") val priceDiscounted: Int
)

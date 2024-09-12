package ya.school.data.model

import com.google.gson.annotations.SerializedName

internal data class ProductFullDTO(
    @SerializedName("_id") val id: String,
    val name: String,
    val price: Int,
    @SerializedName("discounted_price") val priceDiscounted: Int,
    val images: List<String>,
    val description: String
)

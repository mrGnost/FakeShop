package ya.school.data.model

import com.google.gson.annotations.SerializedName

internal data class ProductShortListDTO(
    @SerializedName("Data") val data: List<ProductShortDTO>,
    val count: Int? = null
)

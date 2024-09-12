package ya.school.data.model

import com.google.gson.annotations.SerializedName

data class ProductShortListDTO(
    @SerializedName("Data") val data: List<ProductShortDTO>
)

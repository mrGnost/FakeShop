package ya.school.common.logic.entity

import androidx.annotation.StringRes
import ya.school.common.R

enum class Category(
    @StringRes val nameId: Int,
    val code: String,
    val imageUrl: String
) {
    Computers(
        nameId = R.string.computers,
        code = "computers",
        imageUrl = "https://i.pinimg.com/originals/9e/43/44/9e4344fbf8840d797623bdaedae115e8.png"
    ),
    Clothes(
        nameId = R.string.clothes,
        code = "clothing",
        imageUrl = "https://png.pngtree.com/png-vector/20240131/ourmid/pngtree-clothing-garment-apparel-png-image_11576281.png"
    ),
    Furniture(
        nameId = R.string.furniture,
        code = "furniture",
        imageUrl = "https://png.pngtree.com/png-vector/20240125/ourmid/pngtree-yellow-sofa-furniture-png-image_11548333.png"
    )
}
package ya.school.data.mappers

import ya.school.data.model.ProductFullContainerDTO
import ya.school.data.model.ProductFullDTO
import ya.school.data.model.ProductShortDTO
import ya.school.data.model.ProductShortListDTO
import ya.school.domain.entity.ProductFull
import ya.school.domain.entity.ProductShort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DTOMappers @Inject constructor() {
    private fun productShortDTOToDomain(product: ProductShortDTO): ProductShort = with(product) {
        ProductShort(
            id = id,
            name = name,
            price = price,
            priceDiscounted = priceDiscounted,
            imageUrl = images.firstOrNull()
        )
    }

    fun productShortDTOListToDomain(products: ProductShortListDTO): List<ProductShort> =
        products.data.map {
            productShortDTOToDomain(it)
        }

    fun productFullDTOToDomain(product: ProductFullContainerDTO): ProductFull = with(product.data) {
        ProductFull(
            id = id,
            name = name,
            price = price,
            priceDiscounted = priceDiscounted,
            imagesUrl = images,
            description = description
        )
    }
}
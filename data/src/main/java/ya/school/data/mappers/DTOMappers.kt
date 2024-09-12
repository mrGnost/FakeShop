package ya.school.data.mappers

import ya.school.data.model.ProductShortDTO
import ya.school.data.model.ProductShortListDTO
import ya.school.domain.entity.ProductShort
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DTOMappers @Inject constructor() {
    fun productShortDTOToDomain(product: ProductShortDTO): ProductShort = with(product) {
        ProductShort(
            id = id,
            name = name,
            price = price,
            priceDiscounted = priceDiscounted,
            imageUrl = images.first()
        )
    }

    fun productShortDTOListToDomain(products: ProductShortListDTO): List<ProductShort> =
        products.data.map {
            productShortDTOToDomain(it)
        }
}
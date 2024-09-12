package ya.school.domain.usecase.api

import ya.school.common.logic.entity.DataResult
import ya.school.domain.entity.ProductShort

interface IGetProductsUseCase {
    suspend operator fun invoke(category: String? = null): DataResult<List<ProductShort>>
}